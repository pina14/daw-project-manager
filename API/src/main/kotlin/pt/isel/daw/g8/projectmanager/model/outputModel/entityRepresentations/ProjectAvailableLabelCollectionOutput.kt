package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.ProjectAvailableLabel
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations.ProjectAvailableLabelCollectionSiren

class ProjectAvailableLabelCollectionOutput(val projectName : String,
                                            val availableLabels : List<ProjectAvailableLabel>) : EntityRepresentation {
    override fun toSiren(): SirenModel = SirenModel(ProjectAvailableLabelCollectionSiren(this))
}