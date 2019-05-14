package pt.isel.daw.g8.projectmanager.controller

import org.springframework.web.bind.annotation.*
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.middleware.RequiresAuthentication
import pt.isel.daw.g8.projectmanager.model.inputModel.ProjectAvailableStateInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.services.interfaces.ProjectAvailableStateService
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(ProjectPaths.PROJECT_STATES)
class ProjectAvailableStateController(val projectAvailableStateService : ProjectAvailableStateService) {

    @PostMapping(consumes = ["application/json"])
    @RequiresAuthentication
    fun addProjectAvailableState(request : HttpServletRequest, @RequestBody projectAvailableState : ProjectAvailableStateInput) : OutputModel {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR) as String
        return projectAvailableStateService.addProjectAvailableState(authUsername, projectAvailableState)
    }

    @GetMapping(produces = [SirenModel.mediaType])
    fun getProjectAvailableStates(@RequestParam(ProjectPaths.PROJECT_NAME_VAR) projectName: String) : OutputModel
            = projectAvailableStateService.getProjectAvailableStates(projectName)

    @DeleteMapping
    @RequiresAuthentication
    fun deleteProjectAvailableState(request : HttpServletRequest,
                                    @RequestParam(ProjectPaths.PROJECT_NAME_VAR) projectName: String,
                                    @RequestParam(ProjectPaths.STATE_NAME_VAR) stateName: String) : OutputModel {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR) as String
        return projectAvailableStateService.deleteProjectAvailableState(authUsername, projectName, stateName)
    }
}
