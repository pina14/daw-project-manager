package pt.isel.daw.g8.projectmanager.services.implementations

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import pt.isel.daw.g8.projectmanager.model.Rules
import pt.isel.daw.g8.projectmanager.model.databaseModel.IssueComment
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.BadRequestException
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.NotFoundException
import pt.isel.daw.g8.projectmanager.model.inputModel.IssueCommentInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.IssueCommentCollectionOutput
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.IssueCommentOutput
import pt.isel.daw.g8.projectmanager.repository.IssueCommentRepo
import pt.isel.daw.g8.projectmanager.repository.IssueRepo
import pt.isel.daw.g8.projectmanager.services.interfaces.IssueCommentService

class IssueCommentServiceImpl(private val issueRepo: IssueRepo,
                              private val commentRepo: IssueCommentRepo) : IssueCommentService {

    override fun createComment(issueId : Int, comment: IssueCommentInput): ResponseEntity<Unit> {
        val issueDbReq = issueRepo.findById(issueId)
        if(!issueDbReq.isPresent)
            throw NotFoundException("There isn't a issue with id = '$issueId'.")

        val issueDb = issueDbReq.get()
        if(issueDb.stateName == Rules.ARCHIVED)
            throw BadRequestException("Can't add a comment to an archived issue.")

        val commentDb = IssueComment(comment.commentCreator, issueId, comment.content)
        commentRepo.save(commentDb)

        return ResponseEntity(HttpStatus.CREATED)
    }

    override fun getIssueComments(issueId: Int): OutputModel {
        val issueDb = issueRepo.findById(issueId)

        if(!issueDb.isPresent)
            throw NotFoundException("Issue doesn't exist.")

        val issue = issueDb.get()
        val comments = issue.comments.sortedByDescending { it.creationDate }
        return IssueCommentCollectionOutput(issueId, comments).toSiren()
    }

    override fun getCommentById(commentId: Int): OutputModel {
        val comment = commentRepo.findById(commentId)
        if(!comment.isPresent)
            throw NotFoundException("Doesn't exist a comment with id = '$commentId'.")

        return IssueCommentOutput(comment.get()).toSiren()
    }
}