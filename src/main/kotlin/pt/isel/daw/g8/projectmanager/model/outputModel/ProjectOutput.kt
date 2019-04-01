package pt.isel.daw.g8.projectmanager.model.outputModel

import pt.isel.daw.g8.projectmanager.model.databaseModel.Project
import pt.isel.daw.g8.projectmanager.model.mediaModel.SirenModel

class ProjectOutput(project : Project) : OutputModel{
    lateinit var owner : String
    lateinit var name : String
    lateinit var description : String
    lateinit var defaultIssueState : String

    init {
        owner = project.user.username
        name = project.name
        description = project.description
        defaultIssueState = project.defaultIssueState.stateName
    }

    override fun toSirenRepr(): SirenModel = SirenModel(properties = this)
}