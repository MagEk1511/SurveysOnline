package io.github.magek1511.surveysonline.service

import io.github.magek1511.surveysonline.database.dao.PrivilegeDao
import io.github.magek1511.surveysonline.database.dao.RoleDao
import io.github.magek1511.surveysonline.database.dao.UserDao
import io.github.magek1511.surveysonline.database.entity.Privilege
import io.github.magek1511.surveysonline.database.entity.Role
import io.github.magek1511.surveysonline.database.entity.User
import io.github.magek1511.surveysonline.database.enums.PrivilegeEnum
import io.github.magek1511.surveysonline.database.enums.RoleEnum
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component


@Component
class SetupDataLoader : ApplicationListener<ContextRefreshedEvent> {

    var alreadySetup: Boolean = false

    @Autowired
    private lateinit var userDao: UserDao

    @Autowired
    private lateinit var roleDao: RoleDao

    @Autowired
    private lateinit var privilegeDao: PrivilegeDao

    @Transactional
    override fun onApplicationEvent(event: ContextRefreshedEvent) {

        val readPrivilege = createPrivilegeIfNotFound(PrivilegeEnum.READ_PRIVILEGE)
        val writePrivilege = createPrivilegeIfNotFound(PrivilegeEnum.WRITE_PRIVILEGE)
        val adminPrivileges = arrayListOf(readPrivilege, writePrivilege)
        val userPrivileges = arrayListOf(readPrivilege)
        createRoleIfNotFound(RoleEnum.ROLE_ADMIN, adminPrivileges)
        createRoleIfNotFound(RoleEnum.ROLE_USER, userPrivileges)

        val adminRole: Role? = roleDao.findByName(RoleEnum.ROLE_ADMIN)
        if (adminRole == null) {
            return
        } else {
            if (userDao.findByEmail("test@test.com") == null) {
                val user = User().apply {
                    this.name = "Test"
                    this.email = "test@test.com"
                    this.password = "test"
                    this.roles = arrayListOf(adminRole)
                }
                userDao.save(user)
            }

            alreadySetup = true
        }
    }

    @Transactional
    fun createPrivilegeIfNotFound(privilegeEnum: PrivilegeEnum): Privilege {
        var privilege: Privilege? = privilegeDao.findByName(privilegeEnum)
        if (privilege == null) {
            privilege = Privilege()
            privilege.name = privilegeEnum
        }
        privilegeDao.save(privilege)
        return privilege
    }

    @Transactional
    fun createRoleIfNotFound(roleEnum: RoleEnum, privileges: Collection<Privilege>): Role {
        var role: Role? = roleDao.findByName(roleEnum)
        if (role == null) {
            role = Role().apply {
                this.name = roleEnum
                this.privileges = privileges
            }
        }
        roleDao.save(role)
        return role
    }

}