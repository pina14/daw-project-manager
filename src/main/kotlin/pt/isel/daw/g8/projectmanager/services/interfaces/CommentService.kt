package pt.isel.daw.g8.projectmanager.services.interfaces

import org.springframework.http.ResponseEntity
import pt.isel.daw.g8.projectmanager.model.inputModel.CommentInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel

interface CommentService {
    fun createComment(issueId : Int, comment: CommentInput): ResponseEntity<Unit>
    fun getIssueComments(issueId: Int): OutputModel
    fun getCommentById(commentId : Int): OutputModel
}