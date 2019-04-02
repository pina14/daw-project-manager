package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.Comment

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
}
