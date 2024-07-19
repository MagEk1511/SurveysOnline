package io.github.magek1511.surveysonline.database.dto.response

data class JwtAuthenticationResponse(
    val accessToken: String,
    val refreshToken: String
)
