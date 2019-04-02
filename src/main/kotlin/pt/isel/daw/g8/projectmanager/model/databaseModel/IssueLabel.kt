package pt.isel.daw.g8.projectmanager.model.databaseModel

import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.IssueLabelOutput
import java.io.Serializable
import javax.persistence.*

@Entity(name = "project_issue_label")
data class IssueLabel(@EmbeddedId val projectIssueLabelId : IssueLabelId) : DbModel {
    override fun buildEntityRepresentation(): EntityRepresentation = IssueLabelOutput(this)
}

@Embeddable
data class IssueLabelId(
        @ManyToOne @JoinColumn(name = "project_name") val project : Project,
        @ManyToOne @JoinColumn(name = "issue_id") val issue : Issue,
        @ManyToOne @JoinColumn(name = "label_name") val label: Label) : Serializable