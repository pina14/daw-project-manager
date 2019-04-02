package pt.isel.daw.g8.projectmanager.model.databaseModel

import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.ProjectIssueLabelOutput
import java.io.Serializable
import javax.persistence.*

@Entity(name = "project_issue_label")
data class ProjectIssueLabel(@EmbeddedId val projectIssueLabelId : ProjectIssueLabelId) : DbModel {
    override fun buildEntityRepresentation(): EntityRepresentation = ProjectIssueLabelOutput(this)
}

@Embeddable
data class ProjectIssueLabelId(
        @ManyToOne @JoinColumn(name = "project_name") val project : Project,
        @ManyToOne @JoinColumn(name = "issue_id") val issue : Issue,
        @ManyToOne @JoinColumn(name = "label_name") val label: IssueLabel) : Serializable