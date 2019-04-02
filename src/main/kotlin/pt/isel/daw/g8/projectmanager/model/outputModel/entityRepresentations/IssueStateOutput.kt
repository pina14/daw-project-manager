package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.IssueState

class IssueStateOutput(state : IssueState) : EntityRepresentation {

    var name : String? = null

    init {
        name = state.stateName
    }
}

