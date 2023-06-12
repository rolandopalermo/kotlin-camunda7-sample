package com.camunda.sample.config

import org.camunda.bpm.engine.ProcessEngineConfiguration
import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration
import org.camunda.bpm.spring.boot.starter.configuration.impl.AbstractCamundaConfiguration
import org.springframework.context.annotation.Configuration

@Configuration
class EngineHistoryConfiguration : AbstractCamundaConfiguration() {

    override fun preInit(configuration: SpringProcessEngineConfiguration) {
        configuration.history = ProcessEngineConfiguration.HISTORY_NONE
    }

}
