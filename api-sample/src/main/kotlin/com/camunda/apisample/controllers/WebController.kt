package com.camunda.apisample.controllers

import com.camunda.apisample.dtos.CampaignRequest
import com.camunda.apisample.dtos.CampaignResponse
import com.camunda.apisample.dtos.CreatedCampaignNotificationRequest
import com.camunda.apisample.dtos.KamResponse
import com.camunda.apisample.dtos.OverlapRequest
import com.camunda.apisample.dtos.OverlapResponse
import kotlin.random.Random
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1.0")
class WebController {

    @PostMapping("campaigns/check-overlapping")
    fun validateOverlap(@RequestBody request: OverlapRequest): OverlapResponse {
        val random = Random.nextInt(0, 5)
        return OverlapResponse(random % 2 == 0)
    }

    @PostMapping("campaigns")
    fun createCampaign(@RequestBody request: CampaignRequest): CampaignResponse {
        return CampaignResponse(
            id = Random.nextLong(1, 100),
            budget = request.budget,
            country = request.country,
            startDate = request.startDate,
            endDate = request.endDate,
        )
    }

    @GetMapping("campaigns/{campaign-id}/kams")
    fun getKamEmail(@PathVariable("campaign-id") campaignId: Long): KamResponse {
        return KamResponse(
            name = "Rolando Rodríguez",
            email = "sample@camunda.com",
        )
    }

    @PostMapping("notifications/sent-created-campaign-notification")
    fun sendCreatedEmailNotification(@RequestBody request: CreatedCampaignNotificationRequest): ResponseEntity<Any> {
        return ResponseEntity.noContent().build()
    }

}
