package io.github.magek1511.surveysonline.database.entity

import io.github.magek1511.surveysonline.database.enums.RoleEnum
import jakarta.persistence.*


@Entity
@Table(name="roles")
class Role: AbstractEntity() {

    @Enumerated(EnumType.STRING)
    var name: RoleEnum = RoleEnum.ROLE_USER

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