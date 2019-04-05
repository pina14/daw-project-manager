package pt.isel.daw.g8.projectmanager.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.middleware.RequiresAuthentication
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.ForbiddenException
import pt.isel.daw.g8.projectmanager.model.inputModel.CreateProjectInput
import pt.isel.daw.g8.projectmanager.model.inputModel.UpdateProjectInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.services.interfaces.ProjectService
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(ProjectPaths.PROJECTS)
class ProjectController(val projectService: ProjectService) {

    @PostMapping(consumes = ["application/json"])
    @RequiresAuthentication
    fun createProject(request: HttpServletRequest,
                      @RequestBody project : CreateProjectInput) : ResponseEntity<Unit> {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR)
        if(authUsername != project.username)
            throw ForbiddenException("Authentication credentials are not valid to create this resource.")
        return projectService.createProject(project)
    }

    @GetMapping(produces = [SirenModel.mediaType])
    fun getUserProjects(@PathVariable(ProjectPaths.USERNAME_VAR) username: String) : OutputModel
            = projectService.getUserProjects(username)

    @GetMapping(ProjectPaths.PROJECT_ID, produces = [SirenModel.mediaType])
    fun getProjectByName(@PathVariable(ProjectPaths.PROJECT_NAME_VAR) projectName: String) : OutputModel
            = projectService.getProjectByName(projectName)

    @PutMapping(ProjectPaths.PROJECT_ID, consumes = ["application/json"])
    @RequiresAuthentication
    fun updateProject(request : HttpServletRequest,
                      @PathVariable(ProjectPaths.USERNAME_VAR) username: String,
                      @PathVariable(ProjectPaths.PROJECT_NAME_VAR) projectName: String,
                      @RequestBody project : UpdateProjectInput) : ResponseEntity<Unit> {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR)
        if(authUsername != username)
            throw ForbiddenException("Authentication credentials are not valid to make changes to this user!")

        return projectService.updateProject(projectName, project)
    }

    @DeleteMapping(ProjectPaths.PROJECT_ID)
    @RequiresAuthentication
    fun deleteProject(request : HttpServletRequest,
                      @PathVariable(ProjectPaths.USERNAME_VAR) username: String,
                      @PathVariable(ProjectPaths.PROJECT_NAME_VAR) projectName: String) : ResponseEntity<Unit> {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR)
        if(authUsername != username)
            throw ForbiddenException("Authentication credentials are not valid to make changes to this user!")

        return projectService.deleteProject(authUsername as String, projectName)
    }
}
