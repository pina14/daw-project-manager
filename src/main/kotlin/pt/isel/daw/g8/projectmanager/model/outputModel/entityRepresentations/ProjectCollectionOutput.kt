package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import com.fasterxml.jackson.annotation.JsonIgnore
import pt.isel.daw.g8.projectmanager.model.databaseModel.Project
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations.ProjectCollectionSiren

class ProjectCollectionOutput(@JsonIgnore val username : String, @JsonIgnore val projects : List<Project>) : EntityRepresentation {
    override fun toSiren(): SirenModel = SirenModel(ProjectCollectionSiren(this))
}