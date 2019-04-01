package pt.isel.daw.g8.projectmanager.model.databaseModel

import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.ProjectAvailableLabelOutput
import java.io.Serializable
import javax.persistence.*

@Entity(name = "project_available_label")
data class ProjectAvailableLabel(@EmbeddedId val projectLabelId : ProjectAvailableLabelId) : DbModel {
    override fun buildOutputModel(): OutputModel = ProjectAvailableLabelOutput(this)
}

@Embeddable
data class ProjectAvailableLabelId(
        @ManyToOne @JoinColumn(name = "project_name") val project : Project,
        @ManyToOne @JoinColumn(name = "label_name") val label: IssueLabel) : Serializable