package io.github.magek1511.surveysonline.database.entity

import io.github.magek1511.surveysonline.database.entity.user.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import java.time.Instant

@Entity
class RefreshToken(
    @Column(nullable = false)
    val token: String,
    @Column(nullable = false)
    val expiryDate: Instant,
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: User
): AbstractEntity()