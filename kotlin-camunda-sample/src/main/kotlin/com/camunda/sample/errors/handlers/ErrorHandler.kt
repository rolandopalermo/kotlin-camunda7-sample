package com.camunda.sample.errors.handlers

import com.camunda.sample.errors.dtos.ErrorDto
import com.camunda.sample.errors.exceptions.OverlapException
import javax.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ErrorHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(OverlapException::class)
    fun handleAlreadyExistsException(ex: OverlapException, request: HttpServletRequest?): ResponseEntity<*>? {
        val apiError = ErrorDto(
            ex.message,
            HttpStatus.BAD_REQUEST
        )
        return buildResponse(apiError, HttpStatus.BAD_REQUEST)
    }

    private fun buildResponse(error: ErrorDto, httpStatus: HttpStatus): ResponseEntity<*>? {
        return ResponseEntity<Any?>(error, httpStatus)
    }
}
