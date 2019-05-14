package pt.isel.daw.g8.projectmanager.services.interfaces

import pt.isel.daw.g8.projectmanager.model.inputModel.ProjectAvailableLabelInput
import pt.isel.daw.g8.projectmanager.model.outputModel.EmptyResponseEntity
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel

interface ProjectAvailabeLabelService {
    fun addProjectAvailableLabel(authUsername : String, projectAvailableLabel : ProjectAvailableLabelInput): EmptyResponseEntity
    fun getProjectAvailableLabels(projectName : String): OutputModel
    fun deleteProjectAvailableLabel(authUsername: String, projectName: String, labelName: String): EmptyResponseEntity
}