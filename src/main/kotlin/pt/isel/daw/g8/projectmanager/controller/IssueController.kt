package pt.isel.daw.g8.projectmanager.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.middleware.RequiresAuthentication
import pt.isel.daw.g8.projectmanager.model.inputModel.CreateIssueInput
import pt.isel.daw.g8.projectmanager.model.inputModel.UpdateIssueInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.services.interfaces.IssueService
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(ProjectPaths.ISSUES)
class IssueController(private val issueService: IssueService) : ProjectManagerController {

    @PostMapping(consumes = ["application/json"])
    @RequiresAuthentication
    fun createIssue(request: HttpServletRequest, @RequestBody issue : CreateIssueInput) : ResponseEntity<Unit> {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR) as String
        checkAuthorizationToResource(authUsername, issue.issueCreator)
        return issueService.createIssue(issue)
    }

    @GetMapping(produces = [SirenModel.mediaType])
    fun getProjectIssues(@RequestParam(ProjectPaths.PROJECT_NAME_VAR) projectName: String) : OutputModel = issueService.getProjectIssues(projectName)

    @GetMapping(ProjectPaths.ISSUE_ID, produces = [SirenModel.mediaType])
    fun getIssueById(@PathVariable(ProjectPaths.ISSUE_ID_VAR) issueId: Int) : OutputModel = issueService.getIssueById(issueId)

    @PutMapping(ProjectPaths.ISSUE_ID, consumes = ["application/json"])
    @RequiresAuthentication
    fun updateIssue(request: HttpServletRequest,
                    @PathVariable(ProjectPaths.ISSUE_ID_VAR) issueId: Int,
                    @RequestBody issue : UpdateIssueInput) : ResponseEntity<Unit> {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR) as String

        return issueService.updateIssue(authUsername, issueId, issue)
    }

    @DeleteMapping(ProjectPaths.ISSUE_ID)
    @RequiresAuthentication
    fun deleteIssue(request: HttpServletRequest,
                    @PathVariable(ProjectPaths.ISSUE_ID_VAR) issueId: Int) : ResponseEntity<Unit> {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR) as String

        return issueService.deleteIssue(authUsername, issueId)
    }
}
