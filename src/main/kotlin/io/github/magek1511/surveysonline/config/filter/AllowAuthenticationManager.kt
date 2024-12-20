package io.github.magek1511.surveysonline.config.filter

import jakarta.servlet.http.HttpServletRequest

interface AllowAuthenticationManager {
    fun isRequestAllowed(httpRequest: HttpServletRequest): Boolean
}
