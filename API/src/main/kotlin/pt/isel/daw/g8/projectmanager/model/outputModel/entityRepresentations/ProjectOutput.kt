package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.Project
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations.ProjectSiren

class ProjectOutput(project : Project) : EntityRepresentation {
    var owner : String? = null
    var name : String? = null
    var description : String? = null
    var defaultStateName : String? = null

    init {
        owner = project.user.username
        name = project.name
        description = project.description
        defaultStateName = project.defaultIssueState.stateName
    }

    override fun toSiren(): SirenModel = SirenModel(ProjectSiren(this))
}