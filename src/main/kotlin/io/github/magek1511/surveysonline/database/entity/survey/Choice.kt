package io.github.magek1511.surveysonline.database.entity.survey

import io.github.magek1511.surveysonline.database.entity.AbstractEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn

@Entity
class Choice(
    @Column(nullable = false, length = 128)
    var text: String,
): AbstractEntity()