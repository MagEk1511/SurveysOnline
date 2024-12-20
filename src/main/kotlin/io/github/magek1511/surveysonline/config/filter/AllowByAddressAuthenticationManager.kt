package io.github.magek1511.surveysonline.config.filter

import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AllowByAddressAuthenticationManager(
    @Value("\${security.allowed-ips:}") private val allowedIps: List<String>
): AllowAuthenticationManager {
    override fun isRequestAllowed(httpRequest: HttpServletRequest): Boolean {
        val clientAddr = httpRequest.remoteAddr
        return clientAddr in allowedIps
    }
}