package io.github.magek1511.surveysonline.database.dao

import io.github.magek1511.surveysonline.database.entity.RefreshToken
import org.springframework.data.repository.CrudRepository

interface RefreshTokenDao: CrudRepository<RefreshToken, Long> {

    fun findEntityById(id: Long): RefreshToken?
    fun save(entity: RefreshToken)
    override fun findAll(): MutableIterable<RefreshToken>
    override fun delete(deleted: RefreshToken)
    fun findByToken(token: String): RefreshToken?
    fun findByUserId(id: Long): RefreshToken?
}