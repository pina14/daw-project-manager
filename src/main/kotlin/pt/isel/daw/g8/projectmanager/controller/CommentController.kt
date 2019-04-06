package pt.isel.daw.g8.projectmanager.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.middleware.RequiresAuthentication
import pt.isel.daw.g8.projectmanager.model.inputModel.CommentInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.services.interfaces.CommentService
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(ProjectPaths.COMMENTS)
class CommentController(val commentService : CommentService) : ProjectManagerController {

    @PostMapping(consumes = ["application/json"])
    @RequiresAuthentication
    fun createIssueComment(request: HttpServletRequest,
                           @PathVariable(ProjectPaths.ISSUE_ID_VAR) issueId: Int,
                           @RequestBody comment : CommentInput) : ResponseEntity<Unit> {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR) as String
        checkAuthorizationToResource(authUsername, comment.commentCreator)
        return commentService.createComment(issueId, comment)
    }

    @GetMapping(produces = [SirenModel.mediaType])
    fun getIssueComments(@PathVariable(ProjectPaths.ISSUE_ID_VAR) issueId: Int) : OutputModel = commentService.getIssueComments(issueId)

    @GetMapping(ProjectPaths.COMMENT_ID, produces = [SirenModel.mediaType])
    fun getCommentById(@PathVariable(ProjectPaths.COMMENT_ID_VAR) commentId: Int) : OutputModel = commentService.getCommentById(commentId)
}
