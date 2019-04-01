package pt.isel.daw.g8.projectmanager.model.outputModel

import pt.isel.daw.g8.projectmanager.model.databaseModel.IssueState

class IssueStateOutput(state : IssueState) : OutputModel {

    var name : String? = null

    init {
        name = state.stateName
    }
}

