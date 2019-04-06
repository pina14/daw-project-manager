package pt.isel.daw.g8.projectmanager.services.implementations

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import pt.isel.daw.g8.projectmanager.model.databaseModel.Comment
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.BadRequestException
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.NotFoundException
import pt.isel.daw.g8.projectmanager.model.inputModel.CommentInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.CommentCollectionOutput
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.CommentOutput
import pt.isel.daw.g8.projectmanager.repository.CommentRepo
import pt.isel.daw.g8.projectmanager.repository.IssueRepo
import pt.isel.daw.g8.projectmanager.services.interfaces.CommentService

class CommentImpl(private val issueRepo: IssueRepo,
                  private val commentRepo: CommentRepo) : CommentService {

    override fun createComment(issueId : Int, comment: CommentInput): ResponseEntity<Unit> {
        val issueDbReq = issueRepo.findById(issueId)
        if(!issueDbReq.isPresent)
            throw BadRequestException("There isn't a issue with id = '$issueId'.")

        val issueDb = issueDbReq.get()
        if(issueDb.stateName == "archived")
            throw BadRequestException("Can't add a comment to an archived issue.")

        val commentDb = Comment(comment.commentCreator, issueId, comment.content)
        commentRepo.save(commentDb)

        return ResponseEntity(HttpStatus.CREATED)
    }

    override fun getIssueComments(issueId: Int): OutputModel {
        val issueDb = issueRepo.findById(issueId)

        if(!issueDb.isPresent)
            throw NotFoundException("Issue doesn't exist.")

        val issue = issueDb.get()
        val comments = issue.comments
        return CommentCollectionOutput(issueId, comments).toSiren()
    }

    override fun getCommentById(commentId: Int): OutputModel {
        val comment = commentRepo.findById(commentId)
        if(!comment.isPresent)
            throw NotFoundException("Doesn't exist a comment with this id.")

        return CommentOutput(comment.get()).toSiren()
    }
}