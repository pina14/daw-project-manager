package pt.isel.daw.g8.projectmanager.model

import pt.isel.daw.g8.projectmanager.model.inputModel.StateTransitionInput

object Rules {
    const val OPEN = "open"
    const val CLOSED = "closed"
    const val ARCHIVED = "archived"

    val mandatoryStates = listOf(OPEN, CLOSED, ARCHIVED)

    val mandatoryTransitions = listOf(
            StateTransitionInput(OPEN, CLOSED),
            StateTransitionInput(OPEN, ARCHIVED),
            StateTransitionInput(CLOSED, ARCHIVED)
    )

    fun isMandatoryTransition(from : String, to : String) : Boolean {
        return mandatoryTransitions.any { it.fromState == from && it.toState == to }
    }
}