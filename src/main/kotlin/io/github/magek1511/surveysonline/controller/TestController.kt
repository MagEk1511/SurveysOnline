package io.github.magek1511.surveysonline.controller

import io.github.magek1511.surveysonline.service.UserService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/example")
class TestController(
    private val service: UserService

) {

    @GetMapping
    fun example(): String {
        return "Hello, world!"
    }

    @GetMapping("admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun exampleAdmin(): String {
        return "Hello, admin!"
    }

    @GetMapping("get-admin")
    fun getAdmin(): String {
        service.getAdmin()
        return service.getByUsername(SecurityContextHolder.getContext().authentication.name).roles.toString()
    }

}