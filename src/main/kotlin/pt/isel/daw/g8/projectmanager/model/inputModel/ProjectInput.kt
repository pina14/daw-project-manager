package pt.isel.daw.g8.projectmanager.model.inputModel

import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel

class CreateProjectInput(val name : String,
                         val description : String,
                         val username : String,
                         val defaultStateName : String,
                         val availableLabels : MutableList<LabelInput>? = null,
                         val availableStates : MutableList<StateInput> = mutableListOf(),
                         val availableStateTransitions : MutableList<StateTransitionInput> = mutableListOf()) : InputModel {

    companion object {
        fun getSirenActionFields() : Array<SirenModel.SirenActionField>? {
            val nameField = SirenModel.SirenActionField(name = "name", type = "text")
            val descriptionField = SirenModel.SirenActionField(name = "description", type = "text")
            val usernameField = SirenModel.SirenActionField(name = "username", type = "text")
            val defaultStateNameField = SirenModel.SirenActionField(name = "defaultStateName", type = "text")

            return arrayOf(nameField, descriptionField, usernameField, defaultStateNameField)
        }
    }
}

class UpdateProjectInput(val description : String,
                       val defaultStateName : String) : InputModel {

    companion object {
        fun getSirenActionFields() : Array<SirenModel.SirenActionField>? {
            val descriptionField = SirenModel.SirenActionField(name = "description", type = "text")
            val defaultStateNameField = SirenModel.SirenActionField(name = "defaultStateName", type = "text")

            return arrayOf(descriptionField, defaultStateNameField)
        }
    }
}