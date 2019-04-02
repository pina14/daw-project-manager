package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.Label


class LabelOutput(label : Label) : EntityRepresentation {

    var name : String? = null

    init {
        name = label.labelName
    }
}
