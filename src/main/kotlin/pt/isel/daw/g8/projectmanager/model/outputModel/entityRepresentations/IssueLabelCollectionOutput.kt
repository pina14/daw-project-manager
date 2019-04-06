package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import com.fasterxml.jackson.annotation.JsonIgnore
import pt.isel.daw.g8.projectmanager.model.databaseModel.IssueLabel
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations.IssueLabelCollectionSiren

class IssueLabelCollectionOutput(@JsonIgnore val projectName : String,
                                 @JsonIgnore val issueId : Int,
                                 @JsonIgnore val issueLabels : List<IssueLabel>) : EntityRepresentation {
    override fun toSiren(): SirenModel = SirenModel(IssueLabelCollectionSiren(this))
}