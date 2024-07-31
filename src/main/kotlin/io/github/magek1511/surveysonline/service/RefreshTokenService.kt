package io.github.magek1511.surveysonline.service

import io.github.magek1511.surveysonline.config.AppConfig
import io.github.magek1511.surveysonline.database.dao.RefreshTokenDao
import io.github.magek1511.surveysonline.database.entity.RefreshToken
import io.github.magek1511.surveysonline.database.entity.user.User
import io.github.magek1511.surveysonline.util.exceptions.InvalidTokenException
import org.springframework.stereotype.Service
import java.time.Instant


@Service
class RefreshTokenService(
    private val jwtService: JwtService,
    private val refreshTokenDao: RefreshTokenDao,
    private val appConfig: AppConfig,
) {

    fun generateRefreshToken(user: User): RefreshToken {
        val refreshToken = RefreshToken(
            jwtService.generateToken(user, appConfig.refreshTokenExpirationTime),
            Instant.now().plusMillis(appConfig.refreshTokenExpirationTime.toLong()),
            user
        )

        refreshTokenDao.save(refreshToken)

        return refreshToken
    }

    fun getRefreshToken(user: User): RefreshToken {
        return refreshTokenDao.findByUserId(user.id) ?: generateRefreshToken(user)
    }


    fun verifyExpiration(token: RefreshToken): RefreshToken {
        if (token.expiryDate < Instant.now()) {
            refreshTokenDao.delete(token)
            throw InvalidTokenException.InvalidRefreshTokenException(token.token + " Refresh token is expired. Please make a new login..!")
        }
        return token
    }
}