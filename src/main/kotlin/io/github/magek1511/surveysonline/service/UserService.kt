package io.github.magek1511.surveysonline.service

import io.github.magek1511.surveysonline.database.dao.UserDao
import io.github.magek1511.surveysonline.database.entity.user.User
import io.github.magek1511.surveysonline.database.enums.RoleEnum
import io.github.magek1511.surveysonline.util.exceptions.UserAlreadyExistException
import io.github.magek1511.surveysonline.util.exceptions.UserNotFoundException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service


@Service
class UserService(
    private val userDao: UserDao,
    private val roleService: RoleService
) {
    fun save(user: User): User {
        return userDao.save(user)
    }

    fun create(user: User): User {
        if (userDao.existsByUsername(user.username)) {
            throw UserAlreadyExistException("User with this Username already exists.")
        }
        if (userDao.existsByEmail(user.email)) {
            throw UserAlreadyExistException("User with this Email already exists")
        }

        return save(user)
    }

    fun getByUsername(username: String): User {
        return userDao.findByUsername(username) ?: throw UserNotFoundException("User with current username not found.")
    }

    fun userDetailsService(): UserDetailsService {
        return UserDetailsService { username -> getByUsername(username) }
    }

    fun getCurrentUser(): User {
        val username = SecurityContextHolder.getContext().authentication.name
        return getByUsername(username)
    }

    @Deprecated(
        message = "Only for test"
    )
    fun getAdmin() {
        val user = getCurrentUser()

        user.roles = user.roles.plus(roleService.getRole(RoleEnum.ROLE_ADMIN))

        userDao.save(user)
    }

    fun getAllUsers(): List<User> {
        return userDao.findAll()
    }

}