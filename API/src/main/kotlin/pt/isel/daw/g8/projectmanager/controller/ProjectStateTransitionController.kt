package pt.isel.daw.g8.projectmanager.controller

import org.springframework.web.bind.annotation.*
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.middleware.RequiresAuthentication
import pt.isel.daw.g8.projectmanager.model.inputModel.ProjectStateTransitionInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.services.interfaces.ProjectStateTransitionService
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(ProjectPaths.PROJECT_STATE_TRANSITIONS)
class ProjectStateTransitionController(val projectStateTransitionService : ProjectStateTransitionService) : ProjectManagerController {

    @PostMapping(consumes = ["application/json"])
    @RequiresAuthentication
    fun addProjectStateTransition(request : HttpServletRequest, @RequestBody projectStateTransition : ProjectStateTransitionInput) : OutputModel {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR) as String
        return projectStateTransitionService.addProjectStateTransition(authUsername, projectStateTransition)
    }

    @GetMapping(produces = [SirenModel.mediaType])
    fun getProjectStateTransitions(@RequestParam(ProjectPaths.PROJECT_NAME_VAR) projectName: String) : OutputModel
            = projectStateTransitionService.getProjectStateTransitions(projectName)

    @DeleteMapping
    @RequiresAuthentication
    fun deleteProjectStateTransition(request : HttpServletRequest,
                                    @RequestParam(ProjectPaths.PROJECT_NAME_VAR) projectName: String,
                                    @RequestParam(ProjectPaths.FROM_STATE_VAR) fromState: String,
                                    @RequestParam(ProjectPaths.TO_STATE_VAR) toState: String) : OutputModel {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR) as String
        return projectStateTransitionService.deleteProjectStateTransition(authUsername, projectName, fromState, toState)
    }
}
