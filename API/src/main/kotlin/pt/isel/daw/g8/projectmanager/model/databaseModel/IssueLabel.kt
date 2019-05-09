package pt.isel.daw.g8.projectmanager.model.databaseModel

import java.io.Serializable
import javax.persistence.*

@Entity(name = "issue_label")
data class IssueLabel(@EmbeddedId val projectIssueLabelId : IssueLabelId) : DbModel

@Embeddable
data class IssueLabelId(
        @ManyToOne @JoinColumn(name = "project_name") val project : Project,
        @ManyToOne @JoinColumn(name = "issue_id") val issue : Issue,
        @ManyToOne @JoinColumn(name = "label_name") val label: Label) : Serializable