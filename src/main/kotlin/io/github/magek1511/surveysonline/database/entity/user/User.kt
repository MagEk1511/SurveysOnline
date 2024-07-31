package io.github.magek1511.surveysonline.database.entity.user

import io.github.magek1511.surveysonline.database.entity.AbstractEntity
import jakarta.persistence.*
import jakarta.transaction.Transactional
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
open class User(
    @Column(nullable = false, length = 250)
    var name: String,

    @Column(nullable = false, unique = true, length = 16)
    private var username: String,

    @Column(nullable = false, unique = true, length = 150)
    var email: String,

    @Column(nullable = false, length = 255)
    private var password: String,

    @ManyToMany(
        fetch = FetchType.EAGER
    )
    @JoinTable(
        name = "users_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var roles: Set<Role>
) : AbstractEntity(), UserDetails {

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }

    @Transactional
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {


        val grantedAuthorities = mutableListOf<GrantedAuthority>()

        roles.forEach { role ->
            grantedAuthorities.add(SimpleGrantedAuthority(role.name.name))
        }
        return grantedAuthorities
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    fun setRole(role: Role) {
        roles = roles.plus(role)
    }
}
