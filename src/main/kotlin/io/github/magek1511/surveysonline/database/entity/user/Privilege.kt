package io.github.magek1511.surveysonline.database.entity.user

import io.github.magek1511.surveysonline.database.entity.AbstractEntity
import io.github.magek1511.surveysonline.database.enums.PrivilegeEnum
import jakarta.persistence.*


@Entity
@Table(name = "privileges")
class Privilege(
    @Enumerated(EnumType.STRING)
    var name: PrivilegeEnum = PrivilegeEnum.NO_PRIVILEGE
) : AbstractEntity()


