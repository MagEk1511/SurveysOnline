package io.github.magek1511.surveysonline

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class SurveysOnlineApplication

fun main(args: Array<String>) {
    runApplication<SurveysOnlineApplication>(*args)
}
