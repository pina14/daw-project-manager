package pt.isel.daw.g8.projectmanager.model.outputModel

import pt.isel.daw.g8.projectmanager.model.databaseModel.Project

class ProjectOutput(project : Project) : OutputModel{
    var owner : String? = null
    var name : String? = null
    var description : String? = null
    var defaultIssueState : String? = null

    init {
        owner = project.user.username
        name = project.name
        description = project.description
        defaultIssueState = project.defaultIssueState.stateName
    }
}