package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.Comment
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations.CommentSiren
import java.util.*

class CommentOutput(comment : Comment) : EntityRepresentation {

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

    override fun toSiren(): SirenModel = SirenModel(CommentSiren(this))
}

