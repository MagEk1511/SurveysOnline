package io.github.magek1511.surveysonline.service

import io.github.magek1511.surveysonline.util.JwtParser
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService(
    private val jwtParser: JwtParser,
) {

    fun generateToken(
        userDetails: UserDetails,
        expirationTime: Int
    ): String {
        return generateToken(mapOf(), userDetails, expirationTime)
    }

    fun generateToken(
        extraClaims: Map<String, Claims>,
        userDetails: UserDetails,
        expirationTime: Int
    ): String {
        return Jwts.builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration((Date(System.currentTimeMillis() + expirationTime)))
            .signWith(jwtParser.getSignInKey(), SignatureAlgorithm.HS256).compact()
    }

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = jwtParser.extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    fun isTokenExpired(token: String): Boolean {
        return jwtParser.extractExpiration(token).before(Date())
    }

    fun getUsername(token: String): String {
        return jwtParser.extractUsername(token)
    }


}