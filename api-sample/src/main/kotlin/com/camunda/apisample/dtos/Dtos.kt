package com.camunda.apisample.dtos

import java.math.BigDecimal

data class OverlapRequest(
    val startDate: String,
    val endDate: String,
)

data class OverlapResponse(
    val result: Boolean
)

data class CampaignRequest(
    val startDate: String,
    val endDate: String,
    val country: String,
    val budget: BigDecimal
)

data class CampaignResponse(
    val id: Long,
    val startDate: String,
    val endDate: String,
    val country: String,
    val budget: BigDecimal,
)

data class KamResponse(
    val email: String,
    val name: String,
)

data class CreatedCampaignNotificationRequest(
    val id: Long,
    val startDate: String,
    val endDate: String,
    val country: String,
    val budget: BigDecimal,
    val kamEmail: String,
    val kamName: String,
)