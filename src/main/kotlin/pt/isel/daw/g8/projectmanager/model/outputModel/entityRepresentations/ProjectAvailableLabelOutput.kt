package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.ProjectAvailableLabel
import pt.isel.daw.g8.projectmanager.model.outputModel.mediaType.SirenModel

class ProjectAvailableLabelOutput(availableLabel : ProjectAvailableLabel) : EntityRepresentation {
    var projectName : String? = null
    var labelName : String? = null

    init {
        projectName = availableLabel.projectLabelId.project.name
        labelName = availableLabel.projectLabelId.label.labelName
    }

    override fun toSiren(): SirenModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
