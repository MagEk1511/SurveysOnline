package io.github.magek1511.surveysonline.database.entity.survey

import io.github.magek1511.surveysonline.database.entity.AbstractEntity
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity
class QuestionResponse(
    @ManyToOne
    val question: Question,
    @OneToMany
    val response: List<Choice>
): AbstractEntity()
