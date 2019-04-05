package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.State
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel

class StateOutput(state : State) : EntityRepresentation {

    var name : String? = null

    init {
        name = state.stateName
    }

    override fun toSiren(): SirenModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

