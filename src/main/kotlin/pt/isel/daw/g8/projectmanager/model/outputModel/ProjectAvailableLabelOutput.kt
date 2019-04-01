package pt.isel.daw.g8.projectmanager.model.outputModel

import pt.isel.daw.g8.projectmanager.model.databaseModel.ProjectAvailableLabel

class ProjectAvailableLabelOutput(availableLabel : ProjectAvailableLabel) : OutputModel {
    var projectName : String? = null
    var labelName : String? = null

    init {
        projectName = availableLabel.projectLabelId.project.name
        labelName = availableLabel.projectLabelId.label.labelName
    }
}
