package io.github.magek1511.surveysonline.service

import io.github.magek1511.surveysonline.config.AppConfig
import io.github.magek1511.surveysonline.database.entity.User
import org.springframework.stereotype.Service

@Service
class AccessTokenService(
    private val jwtService: JwtService,
    private val appConfig: AppConfig
) {

    fun generateAccessToken(user: User): String {
        return jwtService.generateToken(
            user,
            appConfig.accessTokenExpirationTime
        )
    }
}