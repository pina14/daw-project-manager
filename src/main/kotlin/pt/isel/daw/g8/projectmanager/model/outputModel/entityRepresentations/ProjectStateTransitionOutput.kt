package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.ProjectStateTransition

class ProjectStateTransitionOutput(stateTransition : ProjectStateTransition) : EntityRepresentation {
    var projectName : String? = null
    var fromState : String? = null
    var toState : String? = null

    init {
        projectName = stateTransition.transitionId.project.name
        fromState = stateTransition.transitionId.fromState.stateName
        toState = stateTransition.transitionId.toState.stateName
    }
}
