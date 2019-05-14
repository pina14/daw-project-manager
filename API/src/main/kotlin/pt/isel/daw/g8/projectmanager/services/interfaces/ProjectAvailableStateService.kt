package pt.isel.daw.g8.projectmanager.services.interfaces

import pt.isel.daw.g8.projectmanager.model.inputModel.ProjectAvailableStateInput
import pt.isel.daw.g8.projectmanager.model.outputModel.EmptyResponseEntity
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel

interface ProjectAvailableStateService {
    fun addProjectAvailableState(authUsername : String, projectAvailableState : ProjectAvailableStateInput): EmptyResponseEntity
    fun getProjectAvailableStates(projectName : String): OutputModel
    fun deleteProjectAvailableState(authUsername: String, projectName: String, stateName: String): EmptyResponseEntity
}