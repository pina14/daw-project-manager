package pt.isel.daw.g8.projectmanager.model.outputModel

import pt.isel.daw.g8.projectmanager.model.databaseModel.ProjectAvailableState

class ProjectAvailableStateOutput(availableState : ProjectAvailableState) : OutputModel {
    var projectName : String? = null
    var stateName : String? = null

    init {
        projectName = availableState.projectStateId.project.name
        stateName = availableState.projectStateId.state.stateName
    }
}
