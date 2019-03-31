package pt.isel.daw.g8.projectmanager.model.databaseModel

import java.io.Serializable
import javax.persistence.*

@Entity(name = "project_issue_label")
data class ProjectIssueLabel(@EmbeddedId val projectIssueLabelId : ProjectIssueLabelId)

@Embeddable
data class ProjectIssueLabelId(
        @ManyToOne @JoinColumn(name = "project_name") val project : Project,
        @ManyToOne @JoinColumn(name = "issue_id") val issue : Issue,
        @ManyToOne @JoinColumn(name = "label_name") val label: IssueLabel) : Serializable