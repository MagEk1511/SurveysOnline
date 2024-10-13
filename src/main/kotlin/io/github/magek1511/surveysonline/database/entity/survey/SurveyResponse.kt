package io.github.magek1511.surveysonline.database.entity.survey

import io.github.magek1511.surveysonline.database.entity.AbstractEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity
class SurveyResponse(
    @Column
    val userId: Long,
    @ManyToOne
    @JoinColumn(name = "survey_id")
    val survey: Survey,
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "survey_response_id")
    val questionResponses: List<QuestionResponse>
): AbstractEntity()