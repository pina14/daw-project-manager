package pt.isel.daw.g8.projectmanager.model.inputModel

import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel

class ProjectStateTransitionInput(val projectName : String, val fromState : String, val toState : String) : InputModel {

    companion object {
        fun getSirenActionFields() : Array<SirenModel.SirenActionField>? {
            val projectNameField = SirenModel.SirenActionField(name = "projectName", type = "text")
            val fromStateField = SirenModel.SirenActionField(name = "fromState", type = "text")
            val toStateField = SirenModel.SirenActionField(name = "toState", type = "text")

            return arrayOf(projectNameField, fromStateField, toStateField)
        }
    }
}