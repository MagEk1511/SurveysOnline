package io.github.magek1511.surveysonline.database.entity

import jakarta.persistence.*


@Entity
class Role: AbstractEntity() {

    var name: String? = null
    @ManyToMany(mappedBy = "roles")
    var users: Collection<User>? = null

    @ManyToMany
    @JoinTable(
        name = "roles_privileges",
        joinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "privilege_id", referencedColumnName = "id")]
    )
    var privileges: Collection<Privilege>? = null
}