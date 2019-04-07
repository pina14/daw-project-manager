package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import com.fasterxml.jackson.annotation.JsonIgnore
import pt.isel.daw.g8.projectmanager.model.databaseModel.ProjectAvailableState
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations.ProjectAvailableStateCollectionSiren

class ProjectAvailableStateCollectionOutput(@JsonIgnore val projectName : String,
                                            @JsonIgnore val availableStates : List<ProjectAvailableState>) : EntityRepresentation {
    override fun toSiren(): SirenModel = SirenModel(ProjectAvailableStateCollectionSiren(this))
}