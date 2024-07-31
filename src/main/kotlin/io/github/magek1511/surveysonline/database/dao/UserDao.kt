package io.github.magek1511.surveysonline.database.dao

import io.github.magek1511.surveysonline.database.entity.user.User

interface UserDao : CommonDao<User> {
    fun findByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean
    fun findByUsername(username: String): User?
    fun existsByUsername(username: String): Boolean
}