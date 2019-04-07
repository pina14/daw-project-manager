package pt.isel.daw.g8.projectmanager.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.middleware.RequiresAuthentication
import pt.isel.daw.g8.projectmanager.model.inputModel.ProjectAvailableLabelInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.services.interfaces.ProjectAvailabeLabelService
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(ProjectPaths.PROJECT_LABELS)
class ProjectAvailableLabelController(val projectAvailableLabelService : ProjectAvailabeLabelService) {

    @PostMapping(consumes = ["application/json"])
    @RequiresAuthentication
    fun addProjectAvailableLabel(request : HttpServletRequest, @RequestBody projectAvailableLabel : ProjectAvailableLabelInput) : ResponseEntity<Unit> {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR) as String
        return projectAvailableLabelService.addProjectAvailableLabel(authUsername, projectAvailableLabel)
    }

    @GetMapping(produces = [SirenModel.mediaType])
    fun getProjectAvailableLabels(@RequestParam(ProjectPaths.PROJECT_NAME_VAR) projectName: String) : OutputModel
            = projectAvailableLabelService.getProjectAvailableLabels(projectName)

    @DeleteMapping
    @RequiresAuthentication
    fun deleteProjectAvailableLabel(request : HttpServletRequest,
                                    @RequestParam(ProjectPaths.PROJECT_NAME_VAR) projectName: String,
                                    @RequestParam(ProjectPaths.LABEL_NAME_VAR) labelName: String) : ResponseEntity<Unit> {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR) as String
        return projectAvailableLabelService.deleteProjectAvailableLabel(authUsername, projectName, labelName)
    }
}
