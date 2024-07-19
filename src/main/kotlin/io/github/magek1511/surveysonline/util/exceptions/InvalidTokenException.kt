package io.github.magek1511.surveysonline.util.exceptions

sealed class InvalidTokenException(message: String): Exception(message) {
    class InvalidRefreshTokenException(message: String): Exception(message)
    class InvalidAccessTokenException(message: String): Exception(message)
}