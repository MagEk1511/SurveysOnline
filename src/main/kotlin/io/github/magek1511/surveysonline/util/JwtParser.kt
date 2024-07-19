package io.github.magek1511.surveysonline.util

import io.github.magek1511.surveysonline.config.AppConfig
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import java.util.function.Function

@Component
class JwtParser(
    appConfig: AppConfig,
    ) {

    private val SECRET_KEY: String = appConfig.secret


    fun <T> extractClaim(token: String, claimsResolver: Function<Claims, T>): T {
        return claimsResolver.apply(
            extractAllClaims(token)
        )
    }

    fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).body
    }

    fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration)
    }



    fun getSignInKey(): Key {
        val keyBytes = Decoders.BASE64.decode(SECRET_KEY)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    fun extractUsername(token: String): String {
        return extractClaim(token, Claims::getSubject)
    }
}