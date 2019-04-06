package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import com.fasterxml.jackson.annotation.JsonIgnore
import pt.isel.daw.g8.projectmanager.model.databaseModel.Comment
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations.CommentCollectionSiren

class CommentCollectionOutput(@JsonIgnore val issueId : Int, @JsonIgnore val comments : List<Comment>) : EntityRepresentation {
    override fun toSiren(): SirenModel = SirenModel(CommentCollectionSiren(this))
}