package io.github.magek1511.surveysonline.database.entity

import jakarta.persistence.*


@Entity
class Privilege: AbstractEntity() {

    var name: String? = null

    @ManyToMany(mappedBy = "privileges")
    var roles: Collection<Role>? = null
}
