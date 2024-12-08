package io.github.magek1511.surveysonline.controller

import io.github.magek1511.surveysonline.database.dto.request.SurveyCreationRequest
import io.github.magek1511.surveysonline.database.entity.survey.Survey
import io.github.magek1511.surveysonline.database.entity.survey.SurveyResponse
import io.github.magek1511.surveysonline.database.entity.user.User
import io.github.magek1511.surveysonline.service.SurveyService
import io.github.magek1511.surveysonline.service.UserService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
class AdminController(
    val surveyService: SurveyService,
    val userService: UserService,
) {
    @PostMapping("survey")
    fun makeSurvey(@RequestBody request: SurveyCreationRequest): Survey {
        return surveyService.createSurvey(request)
//        TODO: Add exceptions for incorrect RequestBody
    }

    @GetMapping()
    fun getSurveys(@RequestParam(required = false) id: Long?): List<Survey> {
        return if (id != null) {
            listOf(surveyService.getSurveyById(id))
        } else {
            surveyService.getAllSurveys()
        }
    }

    @GetMapping("users")
    fun getUsers(): List<User> {
        return userService.getAllUsers()
    }

    @GetMapping("responses")
    fun getAllResponsesForSurvey(@RequestParam surveyId: Long): List<SurveyResponse> {
        return surveyService.getResponsesForSurvey(surveyId)
    }
}