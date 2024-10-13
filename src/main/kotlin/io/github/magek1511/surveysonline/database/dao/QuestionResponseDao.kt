package io.github.magek1511.surveysonline.database.dao

import io.github.magek1511.surveysonline.database.entity.survey.QuestionResponse
import org.springframework.data.repository.CrudRepository

interface QuestionResponseDao: CrudRepository<QuestionResponse, Long> {
}