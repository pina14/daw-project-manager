package pt.isel.daw.g8.projectmanager.model.databaseModel

import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.ProjectAvailableStateOutput
import java.io.Serializable
import javax.persistence.*

@Entity(name = "project_available_state")
data class ProjectAvailableState(@EmbeddedId val projectStateId : ProjectAvailableStateId) : DbModel {
    override fun buildEntityRepresentation(): EntityRepresentation = ProjectAvailableStateOutput(this)
}

@Embeddable
data class ProjectAvailableStateId(
        @ManyToOne @JoinColumn(name = "project_name") val project : Project,
        @ManyToOne @JoinColumn(name = "state_name") val state: State) : Serializable