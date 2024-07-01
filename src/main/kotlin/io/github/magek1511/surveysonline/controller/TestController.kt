package io.github.magek1511.surveysonline.controller

import org.springframework.web.bind.annotation.*

@RestController
class TestController {
    @ResponseBody
    @GetMapping("api/greet")
    fun greet(): String {
        return "Hi"
    }

    @PostMapping("api/greet")
    fun namedGreet(@RequestBody name: String): String {
        return "Hi, $name"
    }

    @ResponseBody
    @GetMapping("api/admin")
    fun adminPanel(): String {
        return "A"
    }

    @ResponseBody
    @GetMapping("api/auth")
    fun auth(): String {
        return "B"
    }


}