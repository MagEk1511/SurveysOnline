package io.github.magek1511.surveysonline.database.entity.survey

import io.github.magek1511.surveysonline.database.entity.AbstractEntity
import io.github.magek1511.surveysonline.database.enums.QuestionEnum
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.OneToMany

@Entity
class Question(
    @Column(nullable = false, length = 127)
    val title: String,
    @Enumerated(EnumType.STRING)
    val type: QuestionEnum,
    @OneToMany
    @JoinTable(
        name = "questions_choices",
        joinColumns = [JoinColumn(name = "question_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "choice_id", referencedColumnName = "id")]
    )
    var choices: List<Choice>,

    ): AbstractEntity()
