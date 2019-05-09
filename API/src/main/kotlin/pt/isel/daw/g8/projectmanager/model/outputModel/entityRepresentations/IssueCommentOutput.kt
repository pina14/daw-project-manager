package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.IssueComment
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations.IssueCommentSiren
import java.util.*

class IssueCommentOutput(comment : IssueComment) : EntityRepresentation {

    var id : Int? = null
    var commentCreator : String? = null
    var issueId : Int? = null
    var content : String? = null
    var creationDate : Date? = null

    init {
        id = comment.id
        commentCreator = comment.commentCreator
        issueId = comment.issueId
        content = comment.content
        creationDate = comment.creationDate
    }

    override fun toSiren(): SirenModel = SirenModel(IssueCommentSiren(this))
}

