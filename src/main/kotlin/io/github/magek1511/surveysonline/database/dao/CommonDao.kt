package io.github.magek1511.surveysonline.database.dao

import io.github.magek1511.surveysonline.database.entity.AbstractEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface CommonDao<T : AbstractEntity> : CrudRepository<T, Long>{
    fun findEntityById(id: Long): T?

    fun save(entity: T)
    override fun findAll(): MutableIterable<T>
    override fun delete(deleted: T)
}