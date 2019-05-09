package pt.isel.daw.g8.projectmanager.model.databaseModel

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "label")
data class Label(@Id @Column(name = "label_name") val labelName : String) : DbModel
