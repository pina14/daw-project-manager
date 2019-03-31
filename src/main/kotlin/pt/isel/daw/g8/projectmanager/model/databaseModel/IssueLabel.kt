package pt.isel.daw.g8.projectmanager.model.databaseModel

import javax.persistence.*

@Entity(name = "issue_label")
data class IssueLabel(@Id @Column(name = "label_name") val labelName : String)
