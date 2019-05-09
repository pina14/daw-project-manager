package pt.isel.daw.g8.projectmanager.services.interfaces

import org.springframework.http.ResponseEntity
import pt.isel.daw.g8.projectmanager.model.inputModel.ProjectStateTransitionInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel

interface ProjectStateTransitionService {
    fun addProjectStateTransition(authUsername : String, projectStateTransition : ProjectStateTransitionInput): ResponseEntity<Unit>
    fun getProjectStateTransitions(projectName : String): OutputModel
    fun deleteProjectStateTransition(authUsername: String, projectName: String, fromState: String, toState : String): ResponseEntity<Unit>
}