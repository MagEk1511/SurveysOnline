package io.github.magek1511.surveysonline.database.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class SignUpRequest(
    @Size(min = 3, max = 16, message = "Username must contain from 3 to 16 characters")
    @NotBlank(message = "Username can't be blank")
    val username: String,
    @Size(min = 5, max = 150, message = "Email must contain from 3 to 16 characters")
    @NotBlank(message = "Email can't be blank")
    @Email(message = "Email address must be in format user@example.com")
    val email: String,
    @Size(min = 8, max = 255, message = "Password must contain from 8 to 255 characters")
    @NotBlank(message = "Password can't be blank")
    val password: String
)