package io.github.magek1511.surveysonline.database.dao

import io.github.magek1511.surveysonline.database.entity.Privilege

interface PrivilegeDao : CommonDao<Privilege> {
    fun findByName(name: String): Privilege?
}