package pt.isel.daw.g8.projectmanager.model.inputModel

import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel

class IssueLabelInput(val issueId : Int, val labelName : String) : InputModel {

    companion object {
        fun getSirenActionFields() : Array<SirenModel.SirenActionField>? {
            val issueIdField = SirenModel.SirenActionField(name = "issueId", type = "number")
            val labelNameField = SirenModel.SirenActionField(name = "labelName", type = "text")

            return arrayOf(issueIdField, labelNameField)
        }
    }
}