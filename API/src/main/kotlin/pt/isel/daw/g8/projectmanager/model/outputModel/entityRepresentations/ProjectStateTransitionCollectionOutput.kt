package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.ProjectStateTransition
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations.ProjectStateTransitionCollectionSiren

class ProjectStateTransitionCollectionOutput(val projectName : String,
                                             val availableTransitions : List<ProjectStateTransition>) : EntityRepresentation {
    override fun toSiren(): SirenModel = SirenModel(ProjectStateTransitionCollectionSiren(this))
}