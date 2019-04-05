package pt.isel.daw.g8.projectmanager.model.inputModel

class CreateProjectInput(val name : String,
                         val description : String,
                         val username : String,
                         val defaultStateName : String,
                         val availableLabels : MutableList<LabelInput>? = null,
                         val availableStates : MutableList<StateInput> = mutableListOf(),
                         val availableStateTransitions : MutableList<StateTransitionInput> = mutableListOf()) : InputModel

class UpdateProjectInput(val description : String,
                       val defaultStateName : String) : InputModel