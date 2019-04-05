package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.Label
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel

class LabelOutput(label : Label) : EntityRepresentation {

    var name : String? = null

    init {
        name = label.labelName
    }

    override fun toSiren(): SirenModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
