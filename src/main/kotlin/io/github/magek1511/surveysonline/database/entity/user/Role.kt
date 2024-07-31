package io.github.magek1511.surveysonline.database.entity.user

import io.github.magek1511.surveysonline.database.entity.AbstractEntity
import io.github.magek1511.surveysonline.database.enums.RoleEnum
import jakarta.persistence.*


@Entity
@Table(name = "roles")
class Role(
    @Enumerated(EnumType.STRING)
    var name: RoleEnum = RoleEnum.ROLE_USER,

    @ManyToMany(
        fetch = FetchType.EAGER,
    )
    @JoinTable(
        name = "roles_privileges",
        joinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "privilege_id", referencedColumnName = "id")]
    )
    private var privileges: Set<Privilege>,
) : AbstractEntity()