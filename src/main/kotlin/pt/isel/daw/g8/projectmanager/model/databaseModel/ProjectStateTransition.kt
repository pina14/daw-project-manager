package pt.isel.daw.g8.projectmanager.model.databaseModel

import java.io.Serializable
import javax.persistence.*

@Entity(name = "project_state_transition")
data class ProjectStateTransition(@EmbeddedId val transitionId : ProjectStateTransitionId)

@Embeddable
data class ProjectStateTransitionId(
        @ManyToOne @JoinColumn(name = "project_name") val project : Project,
        @ManyToOne @JoinColumn(name = "from_state") val fromState: IssueState,
        @ManyToOne @JoinColumn(name = "to_state") val toState: IssueState) : Serializable