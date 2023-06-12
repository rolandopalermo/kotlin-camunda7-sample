package com.camunda.sample.controllers

import com.camunda.sample.dtos.CampaignRequest
import com.camunda.sample.services.CampaignCreationService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1.0/")
class SampleController(
    private val objectMapper: ObjectMapper,
    private val campaignCreationService: CampaignCreationService,
) {

    @GetMapping("greetings")
    fun sample1(): String {
        return "Hello world"
    }

    @PostMapping("campaigns")
    fun createCampaign(@RequestBody request: CampaignRequest) {
        campaignCreationService.run(objectMapper.writeValueAsString(request))
    }

}
