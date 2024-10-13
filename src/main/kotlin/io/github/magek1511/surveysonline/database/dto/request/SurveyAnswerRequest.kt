package io.github.magek1511.surveysonline.database.dto.request

data class SurveyAnswerRequest(
    val surveyId: Long,
    val answers: List<QuestionAnswerRequest>
)

data class QuestionAnswerRequest(
    val questionId: Long,
    val choicesId: List<Long>
)
