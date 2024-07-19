package io.github.magek1511.surveysonline.database.dao

import io.github.magek1511.surveysonline.database.entity.AbstractEntity
import io.github.magek1511.surveysonline.database.enums.AbstractEnum
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface CommonDao<T : AbstractEntity> : CrudRepository<T, Long>{
    fun findEntityById(id: Long): T?
    fun save(entity: T)
    override fun findAll(): MutableIterable<T>
    override fun delete(deleted: T)
    fun findByName(name: String): T?
    fun findByName(name: AbstractEnum): T?
    fun existsByName(name: String): Boolean
    fun existsByName(name: AbstractEnum): Boolean
}