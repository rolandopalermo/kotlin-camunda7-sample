package com.camunda.sample.dtos

import java.math.BigDecimal

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