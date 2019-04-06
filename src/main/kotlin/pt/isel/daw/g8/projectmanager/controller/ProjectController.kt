package pt.isel.daw.g8.projectmanager.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.middleware.RequiresAuthentication
import pt.isel.daw.g8.projectmanager.model.inputModel.CreateProjectInput
import pt.isel.daw.g8.projectmanager.model.inputModel.UpdateProjectInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.services.interfaces.ProjectService
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(ProjectPaths.PROJECTS)
class ProjectController(val projectService: ProjectService) : ProjectManagerController{

    @PostMapping(consumes = ["application/json"])
    @RequiresAuthentication
    fun createProject(request: HttpServletRequest,
                      @RequestBody project : CreateProjectInput) : ResponseEntity<Unit> {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR) as String?
        checkAuthorizationToResource(authUsername, project.username)
        return projectService.createProject(project)
    }

    @GetMapping(produces = [SirenModel.mediaType])
    fun getUserProjects(@RequestParam(ProjectPaths.USERNAME_VAR) username: String) : OutputModel
            = projectService.getUserProjects(username)

    @GetMapping(produces = [SirenModel.mediaType])
    fun getProjectByName(@RequestParam(ProjectPaths.PROJECT_NAME_VAR) projectName: String) : OutputModel
            = projectService.getProjectByName(projectName)

    @PutMapping(consumes = ["application/json"])
    @RequiresAuthentication
    fun updateProject(request : HttpServletRequest,
                      @RequestParam(ProjectPaths.PROJECT_NAME_VAR) projectName: String,
                      @RequestBody project : UpdateProjectInput) : ResponseEntity<Unit> {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR) as String

        return projectService.updateProject(authUsername, projectName, project)
    }

    @DeleteMapping
    @RequiresAuthentication
    fun deleteProject(request : HttpServletRequest,
                      @RequestParam(ProjectPaths.PROJECT_NAME_VAR) projectName: String) : ResponseEntity<Unit> {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR) as String
        return projectService.deleteProject(authUsername, projectName)
    }
}
