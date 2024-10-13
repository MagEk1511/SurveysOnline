package io.github.magek1511.surveysonline.database.dto.request

import io.github.magek1511.surveysonline.database.enums.QuestionEnum

data class QuestionRequest(
    val title: String,
    val type: QuestionEnum,
    val choices: List<ChoiceRequest>
)