package io.github.magek1511.surveysonline.service

import io.github.magek1511.surveysonline.database.dao.UserDao
import io.github.magek1511.surveysonline.util.exceptions.UserNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userDao: UserDao
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return userDao.findByUsername(username) ?: throw UserNotFoundException("User not found")
    }
}