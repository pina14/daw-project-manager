package pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations

import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.ProjectOutput
import pt.isel.daw.g8.projectmanager.model.outputModel.mediaType.SirenModel

class ProjectSiren(override val entity : ProjectOutput) : SirenRepresentation {
    override fun getClasses(): Array<String>? = arrayOf("Project")

    override fun getProperties(): EntityRepresentation? = entity

    override fun getEntities(): Array<SirenModel.SirenEntity>? {
        val labelsEntity = SirenModel.SirenEmbeddedLink(
                arrayOf("Labels", "Collection"),
                arrayOf("/rels/project-available-labels"),
                "/users/${entity.owner}/projects/${entity.name}/labels")

        val statesEntity = SirenModel.SirenEmbeddedLink(
                arrayOf("States", "Collection"),
                arrayOf("/rels/project-available-states"),
                "/users/${entity.owner}/projects/${entity.name}/states")

        val transitionsEntity = SirenModel.SirenEmbeddedLink(
                arrayOf("Transitions", "Collection"),
                arrayOf("/rels/project-available-transitions"),
                "/users/${entity.owner}/projects/${entity.name}/state-transitions")

        val issuesEntity = SirenModel.SirenEmbeddedLink(
                arrayOf("Issues", "Collection"),
                arrayOf("/rels/project-issues"),
                "/users/${entity.owner}/projects/${entity.name}/issues")

        return arrayOf(
                labelsEntity,
                statesEntity,
                transitionsEntity,
                issuesEntity)
    }

    override fun getActions(): Array<SirenModel.SirenAction>? = null

    override fun getLinks(): Array<SirenModel.SirenLink>? {
        return arrayOf(SirenModel.SirenLink(arrayOf("self"), "/users/${entity.owner}/projects/${entity.name}"))
    }
}