package io.github.magek1511.surveysonline.database.entity.survey

import io.github.magek1511.surveysonline.database.entity.AbstractEntity
import jakarta.persistence.*

@Entity
class Survey(
    @Column(nullable = false, length = 255)
    var name: String,
    @OneToMany
    @JoinTable(
        name = "surveys_questions",
        joinColumns = [JoinColumn(name = "survey_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "question_id", referencedColumnName = "id")]
    )
    var questions: List<Question>
): AbstractEntity() {
}