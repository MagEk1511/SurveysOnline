package io.github.magek1511.surveysonline.database.dao

import io.github.magek1511.surveysonline.database.entity.survey.Question
import org.springframework.data.repository.CrudRepository

interface QuestionDao: CrudRepository<Question, Long> {
}