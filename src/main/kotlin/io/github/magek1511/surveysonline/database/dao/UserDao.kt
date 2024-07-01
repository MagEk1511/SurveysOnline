package io.github.magek1511.surveysonline.database.dao

import io.github.magek1511.surveysonline.database.entity.User

interface UserDao : CommonDao<User> {
    fun findByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean
}