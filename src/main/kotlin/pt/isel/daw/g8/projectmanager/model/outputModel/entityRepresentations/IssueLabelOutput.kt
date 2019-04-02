package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.IssueLabel


class IssueLabelOutput(label : IssueLabel) : EntityRepresentation {

    var name : String? = null

    init {
        name = label.labelName
    }
}
