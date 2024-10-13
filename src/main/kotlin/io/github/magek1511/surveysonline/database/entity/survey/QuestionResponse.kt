package io.github.magek1511.surveysonline.database.entity.survey

import io.github.magek1511.surveysonline.database.entity.AbstractEntity
import jakarta.persistence.*

@Entity
class QuestionResponse (
    @ManyToOne
    @JoinColumn(name = "question_id")
    private val question: Question,

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "question_response_chosen_answers",
        joinColumns = [JoinColumn(name = "question_response_id")],
        inverseJoinColumns = [JoinColumn(name = "choice_id")]
    )
    val chosenAnswer: List<Choice>,
): AbstractEntity()