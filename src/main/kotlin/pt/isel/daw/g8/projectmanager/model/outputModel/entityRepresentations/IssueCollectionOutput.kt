package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import com.fasterxml.jackson.annotation.JsonIgnore
import pt.isel.daw.g8.projectmanager.model.databaseModel.Issue
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations.IssueCollectionSiren

class IssueCollectionOutput(@JsonIgnore val projectName : String, @JsonIgnore val issues : List<Issue>) : EntityRepresentation {
    override fun toSiren(): SirenModel = SirenModel(IssueCollectionSiren(this))
}