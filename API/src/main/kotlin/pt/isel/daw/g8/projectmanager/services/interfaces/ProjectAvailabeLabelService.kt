package pt.isel.daw.g8.projectmanager.services.interfaces

import org.springframework.http.ResponseEntity
import pt.isel.daw.g8.projectmanager.model.inputModel.ProjectAvailableLabelInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel

interface ProjectAvailabeLabelService {
    fun addProjectAvailableLabel(authUsername : String, projectAvailableLabel : ProjectAvailableLabelInput): ResponseEntity<Unit>
    fun getProjectAvailableLabels(projectName : String): OutputModel
    fun deleteProjectAvailableLabel(authUsername: String, projectName: String, labelName: String): ResponseEntity<Unit>
}