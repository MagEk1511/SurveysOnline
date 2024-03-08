package io.github.magek1511.surveysonline.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class TestController {
    @ResponseBody
    @GetMapping("/greet")
    fun greet(): String {
        return "Hi"
    }

    @ResponseBody
    @PostMapping("/greet")
    fun namedGreet(@RequestBody name: String): String {
        return "Hi, $name"
    }
}