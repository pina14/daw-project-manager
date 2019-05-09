package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.Issue
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations.IssueCollectionSiren

class IssueCollectionOutput(val projectName : String, val issues : List<Issue>) : EntityRepresentation {
    override fun toSiren(): SirenModel = SirenModel(IssueCollectionSiren(this))
}