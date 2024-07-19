package io.github.magek1511.surveysonline.database.entity

import io.github.magek1511.surveysonline.database.enums.PrivilegeEnum
import jakarta.persistence.*


@Entity
@Table(name = "privileges")
class Privilege(
    @Enumerated(EnumType.STRING)
    var name: PrivilegeEnum = PrivilegeEnum.NO_PRIVILEGE,

    @ManyToMany(mappedBy = "privileges")
    var roles: MutableCollection<Role>? = null,
) : AbstractEntity()


