package pt.isel.daw.g8.projectmanager.model.databaseModel

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity(name = "state")
data class State(@Id @Column(name = "state_name") val stateName : String) : DbModel {

    @OneToMany(mappedBy = "defaultIssueState")
    @JsonIgnore
    lateinit var projects : List<Project>

    @OneToMany(mappedBy = "state")
    @JsonIgnore
    lateinit var issues : List<Issue>
}
