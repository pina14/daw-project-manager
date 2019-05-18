package pt.isel.daw.g8.projectmanager.services.interfaces

import pt.isel.daw.g8.projectmanager.model.inputModel.IssueCommentInput
import pt.isel.daw.g8.projectmanager.model.outputModel.EmptyResponseEntity
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel

interface IssueCommentService {
    fun createComment(comment: IssueCommentInput): EmptyResponseEntity
    fun getIssueComments(issueId: Int): OutputModel
    fun getCommentById(commentId : Int): OutputModel
}