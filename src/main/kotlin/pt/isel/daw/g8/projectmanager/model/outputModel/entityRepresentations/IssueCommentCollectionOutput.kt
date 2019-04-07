package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.IssueComment
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations.IssueCommentCollectionSiren

class IssueCommentCollectionOutput(val issueId : Int, val comments : List<IssueComment>) : EntityRepresentation {
    override fun toSiren(): SirenModel = SirenModel(IssueCommentCollectionSiren(this))
}