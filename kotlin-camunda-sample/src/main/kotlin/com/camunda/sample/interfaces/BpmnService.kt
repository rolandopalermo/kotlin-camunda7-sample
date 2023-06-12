package com.camunda.sample.interfaces

import java.io.Serializable
import org.camunda.bpm.engine.ProcessEngine

interface BpmnService<INPUT : Serializable, OUTPUT : Serializable> {

    fun getProcessName(): String

    fun processEngine(): ProcessEngine

    fun isValidInput(input: INPUT): Boolean

    fun getResult(input: INPUT): OUTPUT?

    fun run(input: INPUT): String?

}
