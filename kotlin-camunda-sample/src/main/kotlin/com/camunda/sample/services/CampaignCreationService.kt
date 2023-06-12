package com.camunda.sample.services

import com.camunda.sample.errors.exceptions.OverlapException
import com.camunda.sample.interfaces.BpmnService
import org.camunda.bpm.engine.ProcessEngine
import org.springframework.stereotype.Service

@Service
class CampaignCreationService(
    private val processEngine: ProcessEngine
) : BpmnService<String, String> {

    override fun getProcessName() = "campaign-creation-process"

    override fun processEngine() = processEngine

    override fun getResult(input: String) = run(input)

    override fun run(input: String): String? {
        if (!isValidInput(input)) {
            throw RuntimeException("Input $input is invalid")
        }

        val processInstance = processEngine()
            .runtimeService
            .createProcessInstanceByKey(getProcessName())
            .setVariable("input", input)
            .executeWithVariablesInReturn()

        val errorCode = processInstance.variables["errorCode"]?.toString()
        if (errorCode != null && (errorCode.compareTo("OVERLAP") == 0)) {
            throw OverlapException("The campaign is overlapped")
        }

        return processInstance.variables["output"] as String?
    }

    override fun isValidInput(input: String) = true

}
