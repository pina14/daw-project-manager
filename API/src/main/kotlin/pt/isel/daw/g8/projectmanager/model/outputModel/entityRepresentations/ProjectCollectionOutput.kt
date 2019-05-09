package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.Project
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations.ProjectCollectionSiren

class ProjectCollectionOutput(val username : String, val projects : List<Project>) : EntityRepresentation {
    override fun toSiren(): SirenModel = SirenModel(ProjectCollectionSiren(this))
}