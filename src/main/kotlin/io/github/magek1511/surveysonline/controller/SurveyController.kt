package io.github.magek1511.surveysonline.controller

import io.github.magek1511.surveysonline.database.dto.request.SurveyAnswerRequest
import io.github.magek1511.surveysonline.database.entity.survey.Survey
import io.github.magek1511.surveysonline.database.entity.survey.SurveyResponse
import io.github.magek1511.surveysonline.service.SurveyService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/survey")
class SurveyController(
    val surveyService: SurveyService,
) {


    @GetMapping
    fun getSurveys(@RequestParam(required = false) id: Long?): List<Survey> {
        return if (id != null) {
            listOf(surveyService.getSurveyById(id))
        } else {
            surveyService.getAllSurveys()
        }
    }

    @PostMapping("/answer")
    fun responseSurvey(
        @RequestBody request: SurveyAnswerRequest,
        @RequestHeader(name = "Authorization") token: String,
    ): SurveyResponse {
        return surveyService.answerSurvey(request, token.substringAfter("Bearer "))
    }
}