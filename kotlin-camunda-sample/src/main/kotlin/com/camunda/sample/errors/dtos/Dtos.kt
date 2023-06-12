package com.camunda.sample.errors.dtos

import org.springframework.http.HttpStatus

data class ErrorDto(
    val message: String?,
    val status: HttpStatus,
)