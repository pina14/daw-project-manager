package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.State

class StateOutput(state : State) : EntityRepresentation {

    var name : String? = null

    init {
        name = state.stateName
    }
}

