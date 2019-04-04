package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.Project
import pt.isel.daw.g8.projectmanager.model.outputModel.mediaType.SirenModel

class ProjectOutput(project : Project) : EntityRepresentation {
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

    override fun toSiren(): SirenModel {
        return SirenModel(
                _class = arrayOf("Project"),
                properties = this,
                entities = getEntities(),
                links = getLinks())
    }

    private fun getEntities(): Array<SirenModel.SirenEntity>? {
        val labelsEntity = SirenModel.SirenEmbeddedLink(
                arrayOf("Labels"),
                arrayOf("/rels/project-available-labels"),
                "/users/$owner/projects/$name/labels")

        val statesEntity = SirenModel.SirenEmbeddedLink(
                arrayOf("States"),
                arrayOf("/rels/project-available-states"),
                "/users/$owner/projects/$name/states")

        val transitionsEntity = SirenModel.SirenEmbeddedLink(
                arrayOf("Transitions"),
                arrayOf("/rels/project-available-transitions"),
                "/users/$owner/projects/$name/state-transitions")

        val issuesEntity = SirenModel.SirenEmbeddedLink(
                arrayOf("Issues"),
                arrayOf("/rels/project-issues"),
                "/users/$owner/projects/$name/issues")

        return arrayOf(
                labelsEntity,
                statesEntity,
                transitionsEntity,
                issuesEntity)
    }

    private fun getLinks(): Array<SirenModel.SirenLink>? {
        return arrayOf(SirenModel.SirenLink(arrayOf("self"), "/users/$owner/projects/$name"))
    }
}