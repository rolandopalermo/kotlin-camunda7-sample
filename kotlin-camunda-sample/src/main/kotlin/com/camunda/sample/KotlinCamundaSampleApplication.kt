package com.camunda.sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinCamundaSampleApplication

fun main(args: Array<String>) {
	runApplication<KotlinCamundaSampleApplication>(*args)
}
