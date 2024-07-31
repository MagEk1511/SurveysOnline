package io.github.magek1511.surveysonline.service

import io.github.magek1511.surveysonline.database.dto.request.RefreshTokenRequest
import io.github.magek1511.surveysonline.database.dto.request.SignInRequest
import io.github.magek1511.surveysonline.database.dto.request.SignUpRequest
import io.github.magek1511.surveysonline.database.dto.response.JwtAuthenticationResponse
import io.github.magek1511.surveysonline.database.entity.user.User
import io.github.magek1511.surveysonline.database.enums.RoleEnum
import io.github.magek1511.surveysonline.util.exceptions.InvalidTokenException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val userService: UserService,
    private val roleService: RoleService,
    private val accessTokenService: AccessTokenService,
    private val refreshTokenService: RefreshTokenService,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
) {

    fun signUp(request: SignUpRequest): JwtAuthenticationResponse {
        val user = User(
            name = request.username,
            username = request.username,
            email = request.email,
            password = passwordEncoder.encode(request.password),
            roles = hashSetOf(roleService.getRole(RoleEnum.ROLE_USER))
        )

        userService.create(user)
        val accessToken = accessTokenService.generateAccessToken(user)
        val refreshToken = refreshTokenService.generateRefreshToken(user)

        return JwtAuthenticationResponse(accessToken, refreshToken.token)
    }

    fun signIn(request: SignInRequest): JwtAuthenticationResponse {
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(
            request.username,
            request.password
        ))

        val user = userService
            .getByUsername(request.username)


        val accessToken = accessTokenService.generateAccessToken(user)
        val refreshToken = refreshTokenService.getRefreshToken(user)

        refreshTokenService.verifyExpiration(refreshToken)

        return JwtAuthenticationResponse(accessToken, refreshToken.token)
    }

    fun refreshToken(request: RefreshTokenRequest): JwtAuthenticationResponse {
        val user = userService.getByUsername(request.username)
        val refreshToken = refreshTokenService.getRefreshToken(user)
        if (refreshToken.token == request.refreshToken) {
            refreshTokenService.verifyExpiration(refreshToken)

            val newRefreshToken = refreshTokenService.getRefreshToken(user)
            val newAccessToken = accessTokenService.generateAccessToken(user)

            return JwtAuthenticationResponse(newAccessToken, newRefreshToken.token)
        }

        throw InvalidTokenException.InvalidRefreshTokenException("Invalid refresh token")
    }
}