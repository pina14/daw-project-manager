package pt.isel.daw.g8.projectmanager.services.interfaces

import pt.isel.daw.g8.projectmanager.model.inputModel.CreateProjectInput
import pt.isel.daw.g8.projectmanager.model.inputModel.UpdateProjectInput
import pt.isel.daw.g8.projectmanager.model.outputModel.EmptyResponseEntity
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel

interface ProjectService {
    fun createProject(project: CreateProjectInput): EmptyResponseEntity
    fun getUserProjects(username: String): OutputModel
    fun getProjectByName(projectName : String): OutputModel
    fun updateProject(authUsername: String, projectName: String, project: UpdateProjectInput): EmptyResponseEntity
    fun deleteProject(authUsername : String, projectName: String): EmptyResponseEntity
}