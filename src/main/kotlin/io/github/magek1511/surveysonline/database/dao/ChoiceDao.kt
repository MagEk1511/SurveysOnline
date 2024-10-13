package io.github.magek1511.surveysonline.database.dao

import io.github.magek1511.surveysonline.database.entity.survey.Choice
import org.springframework.data.repository.CrudRepository

interface ChoiceDao: CrudRepository<Choice, Long> {
}