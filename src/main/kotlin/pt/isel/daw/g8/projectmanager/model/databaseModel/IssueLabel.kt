package pt.isel.daw.g8.projectmanager.model.databaseModel

import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "issue_label")
data class IssueLabel(@Id @Column(name = "label_name") val labelName : String) : DbModel {

    override fun buildOutputModel(): OutputModel = pt.isel.daw.g8.projectmanager.model.outputModel.IssueLabelOutput(this)
}
