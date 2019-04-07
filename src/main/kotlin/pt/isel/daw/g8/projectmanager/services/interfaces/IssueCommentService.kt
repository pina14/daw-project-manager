package pt.isel.daw.g8.projectmanager.services.interfaces

import org.springframework.http.ResponseEntity
import pt.isel.daw.g8.projectmanager.model.inputModel.IssueCommentInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel

interface IssueCommentService {
    fun createComment(issueId : Int, comment: IssueCommentInput): ResponseEntity<Unit>
    fun getIssueComments(issueId: Int): OutputModel
    fun getCommentById(commentId : Int): OutputModel
}