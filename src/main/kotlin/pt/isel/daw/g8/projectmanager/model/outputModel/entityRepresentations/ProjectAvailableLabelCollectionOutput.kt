package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import com.fasterxml.jackson.annotation.JsonIgnore
import pt.isel.daw.g8.projectmanager.model.databaseModel.ProjectAvailableLabel
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations.ProjectAvailableLabelCollectionSiren

class ProjectAvailableLabelCollectionOutput(@JsonIgnore val projectName : String,
                                            @JsonIgnore val availableLabels : List<ProjectAvailableLabel>) : EntityRepresentation {
    override fun toSiren(): SirenModel = SirenModel(ProjectAvailableLabelCollectionSiren(this))
}