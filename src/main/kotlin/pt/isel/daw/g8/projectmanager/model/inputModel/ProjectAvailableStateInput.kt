package pt.isel.daw.g8.projectmanager.model.inputModel

import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel

class ProjectAvailableStateInput(val projectName : String, val stateName : String) : InputModel {

    companion object {
        fun getSirenActionFields() : Array<SirenModel.SirenActionField>? {
            val projectNameField = SirenModel.SirenActionField(name = "projectName", type = "text")
            val stateNameField = SirenModel.SirenActionField(name = "stateName", type = "text")

            return arrayOf(projectNameField, stateNameField)
        }
    }
}