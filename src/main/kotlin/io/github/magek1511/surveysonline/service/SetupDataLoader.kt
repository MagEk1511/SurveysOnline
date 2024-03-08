package io.github.magek1511.surveysonline.service

import io.github.magek1511.surveysonline.database.dao.PrivilegeDao
import io.github.magek1511.surveysonline.database.dao.RoleDao
import io.github.magek1511.surveysonline.database.dao.UserDao
import io.github.magek1511.surveysonline.database.entity.Privilege
import io.github.magek1511.surveysonline.database.entity.Role
import io.github.magek1511.surveysonline.database.entity.User
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
        if (alreadySetup) {
            println("Already setuped")
            return
        }
        println("New setup")
        val readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE")
        val writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE")
        val adminPrivileges = arrayListOf(readPrivilege, writePrivilege)
        val userPrivileges = arrayListOf(readPrivilege)
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges)
        createRoleIfNotFound("ROLE_USER", userPrivileges)

        val adminRole: Role? = roleDao.findByName("ROLE_ADMIN")
        if (adminRole == null) {
            return
        } else {
            val user = User().apply {
                this.name = "Test"
                this.email = "test@test.com"
                this.password = "test"
                this.roles = arrayListOf(adminRole)
            }
            userDao.save(user)

            alreadySetup = true
        }
    }

    @Transactional
    fun createPrivilegeIfNotFound(name: String): Privilege {
        var privilege: Privilege? = privilegeDao.findByName(name)
        if (privilege == null) {
            privilege = Privilege()
            privilege.name = name
        }
        privilegeDao.save(privilege)
        return privilege
    }

    @Transactional
    fun createRoleIfNotFound(name: String, privileges: Collection<Privilege>): Role {
        var role: Role? = roleDao.findByName(name)
        if (role == null) {
            role = Role().apply {
                this.name = name
                this.privileges = privileges
            }
        }
        roleDao.save(role)
        return role
    }

}