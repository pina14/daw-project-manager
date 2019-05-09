package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.IssueLabel
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations.IssueLabelCollectionSiren

class IssueLabelCollectionOutput(val projectName : String,
                                 val issueId : Int,
                                 val issueLabels : List<IssueLabel>) : EntityRepresentation {
    override fun toSiren(): SirenModel = SirenModel(IssueLabelCollectionSiren(this))
}