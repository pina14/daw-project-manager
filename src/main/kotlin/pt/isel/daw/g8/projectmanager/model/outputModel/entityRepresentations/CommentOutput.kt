package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.Comment
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel

class CommentOutput(comment : Comment) : EntityRepresentation {

    var id : Int? = null
    var issue : Int? = null
    var content : String? = null
    var creationDate : String? = null

    init {
        id = comment.id
        issue = comment.issue.id
        content = comment.content
        creationDate = comment.creationDate.toString()
    }

    override fun toSiren(): SirenModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

