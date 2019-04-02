package pt.isel.daw.g8.projectmanager.model.databaseModel

import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.ProjectStateTransitionOutput
import java.io.Serializable
import javax.persistence.*

@Entity(name = "project_state_transition")
data class ProjectStateTransition(@EmbeddedId val transitionId : ProjectStateTransitionId) : DbModel {
    override fun buildEntityRepresentation(): EntityRepresentation = ProjectStateTransitionOutput(this)
}

@Embeddable
data class ProjectStateTransitionId(
        @ManyToOne @JoinColumn(name = "project_name") val project : Project,
        @ManyToOne @JoinColumn(name = "from_state") val fromState: State,
        @ManyToOne @JoinColumn(name = "to_state") val toState: State) : Serializable