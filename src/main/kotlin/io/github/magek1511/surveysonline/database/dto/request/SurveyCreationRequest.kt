package io.github.magek1511.surveysonline.database.dto.request

data class SurveyCreationRequest(
    val name: String,
    val questions: List<QuestionRequest>
)
