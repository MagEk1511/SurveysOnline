package io.github.magek1511.surveysonline.config

import org.springframework.boot.context.properties.ConfigurationProperties


/**
 * App config
 *
 * @property secret Secret code for JWT
 * @property accessTokenExpirationTime Expiration time for JWT in milliseconds. Default: 1'440'000
 * @constructor Create empty App config
 */
@ConfigurationProperties(prefix = "app")
data class AppConfig (
    var secret: String,
    var accessTokenExpirationTime: Int = 1000 * 60 * 24,
    var refreshTokenExpirationTime: Int = 1000 * 60 * 24 * 30
)

