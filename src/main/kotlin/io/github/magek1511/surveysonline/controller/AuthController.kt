package io.github.magek1511.surveysonline.controller

import io.github.magek1511.surveysonline.database.dto.request.RefreshTokenRequest
import io.github.magek1511.surveysonline.database.dto.request.SignInRequest
import io.github.magek1511.surveysonline.database.dto.request.SignUpRequest
import io.github.magek1511.surveysonline.database.dto.response.JwtAuthenticationResponse
import io.github.magek1511.surveysonline.service.AuthenticationService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/auth")
class AuthController(
    private val authenticationService: AuthenticationService
) {
    @PostMapping("sign-up")
    fun signUp(@RequestBody request: @Valid SignUpRequest?): JwtAuthenticationResponse {
        return authenticationService.signUp(request ?: throw Exception("Something went wrong"))
    }

    @PostMapping("sign-in")
    fun signIn(@RequestBody request: @Valid SignInRequest?): JwtAuthenticationResponse {
        return authenticationService.signIn(request ?: throw Exception("Something went wrong"))
    }

    @PostMapping("refresh-token")
    fun refreshToken(@RequestBody request: @Valid RefreshTokenRequest?): JwtAuthenticationResponse {
        return authenticationService.refreshToken(request ?: throw Exception("Something went wrong"))
    }
}