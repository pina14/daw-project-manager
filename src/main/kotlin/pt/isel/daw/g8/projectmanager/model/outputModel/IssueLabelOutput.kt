package pt.isel.daw.g8.projectmanager.model.outputModel

import pt.isel.daw.g8.projectmanager.model.databaseModel.IssueLabel


class IssueLabelOutput(label : IssueLabel) : OutputModel{

    var name : String? = null

    init {
        name = label.labelName
    }
}
