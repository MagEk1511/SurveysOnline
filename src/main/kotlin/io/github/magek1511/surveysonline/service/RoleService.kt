package io.github.magek1511.surveysonline.service

import io.github.magek1511.surveysonline.database.dao.RoleDao
import io.github.magek1511.surveysonline.database.entity.user.Role
import io.github.magek1511.surveysonline.database.enums.RoleEnum
import io.github.magek1511.surveysonline.util.exceptions.RoleNotFoundException
import org.springframework.stereotype.Service

@Service
class RoleService(
    private val roleDao: RoleDao
) {

    fun getRole(role: RoleEnum): Role {
        return roleDao.findByName(role) ?: throw RoleNotFoundException("Role ${role.name} not found")
    }
}
