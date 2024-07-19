package io.github.magek1511.surveysonline

import io.github.magek1511.surveysonline.config.AppConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication


@SpringBootApplication
@EnableConfigurationProperties(AppConfig::class)
class SurveysOnlineApplication

fun main(args: Array<String>) {
    runApplication<SurveysOnlineApplication>(*args)
}
