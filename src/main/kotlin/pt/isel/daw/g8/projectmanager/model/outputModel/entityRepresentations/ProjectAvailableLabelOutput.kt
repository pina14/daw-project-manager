package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.ProjectAvailableLabel
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations.ProjectAvailableLabelSiren

class ProjectAvailableLabelOutput(availableLabel : ProjectAvailableLabel) : EntityRepresentation {
    var projectName : String? = null
    var labelName : String? = null

    init {
        projectName = availableLabel.projectLabelId.project.name
        labelName = availableLabel.projectLabelId.label.labelName
    }

    override fun toSiren(): SirenModel = SirenModel(ProjectAvailableLabelSiren(this))
}
