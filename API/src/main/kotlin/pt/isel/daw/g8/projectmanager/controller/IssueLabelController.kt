package pt.isel.daw.g8.projectmanager.controller

import org.springframework.web.bind.annotation.*
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.middleware.RequiresAuthentication
import pt.isel.daw.g8.projectmanager.model.inputModel.IssueLabelInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.services.interfaces.IssueLabelService
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(ProjectPaths.ISSUE_LABELS)
class IssueLabelController(val issueLabelService : IssueLabelService) : ProjectManagerController {

    @PostMapping(consumes = ["application/json"])
    @RequiresAuthentication
    fun addIssueLabel(request : HttpServletRequest, @RequestBody issueLabel : IssueLabelInput) : OutputModel {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR) as String
        return issueLabelService.addIssueLabel(authUsername, issueLabel)
    }

    @GetMapping(produces = [SirenModel.mediaType])
    fun getIssueLabels(@RequestParam(ProjectPaths.ISSUE_ID_VAR) issueId: Int) : OutputModel = issueLabelService.getIssueLabels(issueId)

    @DeleteMapping
    @RequiresAuthentication
    fun deleteIssueLabel(request : HttpServletRequest,
                         @RequestParam(ProjectPaths.ISSUE_ID_VAR) issueId : Int,
                         @RequestParam(ProjectPaths.LABEL_NAME_VAR) labelName : String) : OutputModel {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR) as String
        return issueLabelService.deleteIssueLabel(authUsername, issueId, labelName)
    }
}
