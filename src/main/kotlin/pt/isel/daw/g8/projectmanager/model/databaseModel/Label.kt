package pt.isel.daw.g8.projectmanager.model.databaseModel

import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.LabelOutput
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "issue_label")
data class Label(@Id @Column(name = "label_name") val labelName : String) : DbModel {

    override fun buildEntityRepresentation(): EntityRepresentation = LabelOutput(this)
}
