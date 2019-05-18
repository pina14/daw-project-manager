package pt.isel.daw.g8.projectmanager.controller

import org.springframework.web.bind.annotation.*
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.middleware.RequiresAuthentication
import pt.isel.daw.g8.projectmanager.model.inputModel.IssueCommentInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.services.interfaces.IssueCommentService
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(ProjectPaths.ISSUE_COMMENTS)
class IssueCommentController(val commentService : IssueCommentService) : ProjectManagerController {

    @PostMapping(consumes = ["application/json"])
    @RequiresAuthentication
    fun createIssueComment(request: HttpServletRequest, @RequestBody comment : IssueCommentInput) : OutputModel {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR) as String
        checkAuthorizationToResource(authUsername, comment.commentCreator)
        return commentService.createComment(comment)
    }

    @GetMapping(produces = [SirenModel.mediaType])
    fun getIssueComments(@RequestParam(ProjectPaths.ISSUE_ID_VAR) issueId: Int) : OutputModel = commentService.getIssueComments(issueId)

    @GetMapping(ProjectPaths.ISSUE_COMMENT_ID, produces = [SirenModel.mediaType])
    fun getCommentById(@PathVariable(ProjectPaths.COMMENT_ID_VAR) commentId: Int) : OutputModel = commentService.getCommentById(commentId)
}
