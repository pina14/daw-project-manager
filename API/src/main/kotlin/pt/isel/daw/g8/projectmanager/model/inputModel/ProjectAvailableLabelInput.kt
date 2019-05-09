package pt.isel.daw.g8.projectmanager.model.inputModel

import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel

class ProjectAvailableLabelInput(val projectName : String, val labelName : String) : InputModel {

    companion object {
        fun getSirenActionFields() : Array<SirenModel.SirenActionField>? {
            val projectNameField = SirenModel.SirenActionField(name = "projectName", type = "text")
            val labelNameField = SirenModel.SirenActionField(name = "labelName", type = "text")

            return arrayOf(projectNameField, labelNameField)
        }
    }
}