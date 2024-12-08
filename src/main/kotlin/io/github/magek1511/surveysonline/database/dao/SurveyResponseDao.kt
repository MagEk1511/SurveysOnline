package io.github.magek1511.surveysonline.database.dao

import io.github.magek1511.surveysonline.database.entity.survey.SurveyResponse
import org.springframework.data.repository.CrudRepository

interface SurveyResponseDao: CrudRepository<SurveyResponse, Long> {
    fun findAllBySurveyId(id: Long): List<SurveyResponse>
}