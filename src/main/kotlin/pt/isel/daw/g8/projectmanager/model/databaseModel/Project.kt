package pt.isel.daw.g8.projectmanager.model.databaseModel

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity(name = "project")
data class Project(
        @Id @Column(name = "project_name") val name : String,
        @Column(name = "description") val description : String,
        @Column(name = "username") val username: String,
        @Column(name = "default_issue_state") val defaultIssueStateName : String) : DbModel {

    @ManyToOne
    @JoinColumn(name = "username", insertable = false, updatable = false)
    lateinit var user: UserInfo

    @ManyToOne
    @JoinColumn(name = "default_issue_state", insertable = false, updatable = false)
    lateinit var defaultIssueState : State

    @OneToMany(mappedBy = "projectLabelId.project")
    @JsonIgnore
    lateinit var availableLabels : List<ProjectAvailableLabel>

    @OneToMany(mappedBy = "projectStateId.project")
    @JsonIgnore
    lateinit var availableStates : List<ProjectAvailableState>

    @OneToMany(mappedBy = "transitionId.project")
    @JsonIgnore
    lateinit var availableStateTransitions : List<ProjectStateTransition>

    @OneToMany(mappedBy = "project")
    @JsonIgnore
    lateinit var issues : List<Issue>
}