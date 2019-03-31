package pt.isel.daw.g8.projectmanager.model.databaseModel

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity(name = "issue_state")
data class IssueState(@Id @Column(name = "state_name") val stateName : String) {

    @OneToMany(mappedBy = "defaultIssueState")
    @JsonIgnore
    lateinit var projects : List<Project>

    @OneToMany(mappedBy = "state")
    @JsonIgnore
    lateinit var issues : List<Issue>
}
