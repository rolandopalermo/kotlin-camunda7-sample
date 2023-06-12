package com.camunda.sample.delegates

import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClientResponseException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder

@Component
class RestClientDelegate(
    @Value("\${microservices.api-base-url}")
    private val microservicesApiBaseUrl: String,
    private val restTemplate: RestTemplate
) : JavaDelegate {

    private val logger by lazy { LoggerFactory.getLogger(RestClientDelegate::class.java) }

    override fun execute(execution: DelegateExecution) {
        val params = execution.toParams()

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val response = exchange(params.uri, params.httpMethod, HttpEntity(params.payload, headers))

        execution.setVariable("httpStatusCode", response.statusCode.value())
        execution.setVariable("httpResponse", response.body)

        logger.info("[${response.statusCode}] ${response.body}")
    }

    fun exchange(uri: String, httpMethod: HttpMethod, requestEntity: HttpEntity<Any>): ResponseEntity<String> = try {
        restTemplate.exchange(
            UriComponentsBuilder
                .fromHttpUrl(microservicesApiBaseUrl)
                .path(uri)
                .build()
                .toUri(),
            httpMethod,
            requestEntity,
            String::class.java
        )
    } catch (ex: RestClientResponseException) {
        logger.error("An error has occurred calling HTTP endpoint: {}", ex.responseBodyAsString)
        ResponseEntity
            .status(ex.rawStatusCode)
            .body(ex.responseBodyAsString)
    }

    private fun DelegateExecution.toParams() = Params(
        uri = getVariable("uri").toString(),
        payload = getVariable("payload").toString(),
        httpMethod = HttpMethod.valueOf(getVariable("httpMethod").toString().uppercase()),
    )

    data class Params(
        val uri: String,
        val payload: String,
        val httpMethod: HttpMethod,
    )

}
