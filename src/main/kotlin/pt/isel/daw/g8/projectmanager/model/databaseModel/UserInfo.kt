package pt.isel.daw.g8.projectmanager.model.databaseModel

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity(name = "user_info")
data class UserInfo(
        @Id @Column(name = "username") val username : String,
        @Column(name = "password") val password : String) {

    @OneToMany(mappedBy = "username")
    @JsonIgnore
    lateinit var projects : List<Project>
}