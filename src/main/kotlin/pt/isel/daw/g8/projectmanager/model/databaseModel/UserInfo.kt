package pt.isel.daw.g8.projectmanager.model.databaseModel

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity(name = "user_info")
data class UserInfo(
        @Id @Column(name = "username") val username : String,
        @Column(name = "password") val password : String,
        @Column(name = "email") val email : String,
        @Column(name = "full_name") val fullName : String) : DbModel {
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    lateinit var projects : List<Project>

    @OneToMany(mappedBy = "creator")
    @JsonIgnore
    lateinit var createdIssues : List<Issue>
}