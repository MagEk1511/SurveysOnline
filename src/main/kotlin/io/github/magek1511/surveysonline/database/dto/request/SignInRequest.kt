package io.github.magek1511.surveysonline.database.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class SignInRequest(
    @Size(min = 3, max = 16, message = "Username must contain from 3 to 16 characters")
    @NotBlank(message = "Username can't be blank")
    val username: String,
    @Size(min = 8, max = 255, message = "Password must contain from 8 to 255 characters")
    @NotBlank(message = "Password can't be blank")
    val password: String
)
