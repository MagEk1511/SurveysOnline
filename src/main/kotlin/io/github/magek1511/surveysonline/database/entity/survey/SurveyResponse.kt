package io.github.magek1511.surveysonline.database.entity.survey

import io.github.magek1511.surveysonline.database.entity.AbstractEntity
import io.github.magek1511.surveysonline.database.entity.user.User
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity
class SurveyResponse(
    @ManyToOne
    @JoinColumn(name = "survey_id")
    val survey: Survey,
    @OneToMany(mappedBy = "survey_response", cascade = [CascadeType.ALL])
    val questionResponses: List<QuestionResponse>,
    @ManyToOne
    val user: User
): AbstractEntity()