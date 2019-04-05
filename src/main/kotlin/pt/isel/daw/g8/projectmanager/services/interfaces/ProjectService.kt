package pt.isel.daw.g8.projectmanager.services.interfaces

import org.springframework.http.ResponseEntity
import pt.isel.daw.g8.projectmanager.model.inputModel.CreateProjectInput
import pt.isel.daw.g8.projectmanager.model.inputModel.UpdateProjectInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel

interface ProjectService {
    fun createProject(project: CreateProjectInput): ResponseEntity<Unit>
    fun getUserProjects(username: String): OutputModel
    fun getProjectByName(projectName : String): OutputModel
    fun updateProject(projectName : String, project: UpdateProjectInput): ResponseEntity<Unit>
    fun deleteProject(authUsername : String, projectName: String): ResponseEntity<Unit>
}