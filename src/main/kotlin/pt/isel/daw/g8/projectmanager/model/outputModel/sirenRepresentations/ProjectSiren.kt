package pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations

import org.springframework.http.HttpMethod
import pt.isel.daw.g8.projectmanager.model.inputModel.UpdateProjectInput
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.ProjectOutput

class ProjectSiren(override val entity : ProjectOutput) : SirenRepresentation {
    override fun getClasses(): Array<String>? = arrayOf("Project")

    override fun getProperties(): EntityRepresentation? = entity

    override fun getEntities(): Array<SirenModel.SirenEntity>? {
        val labelsEntity = SirenModel.SirenEmbeddedLink(
                arrayOf("Labels", "Collection"),
                arrayOf("/rels/project-available-labels"),
                "/projects/${entity.name}/labels")

        val statesEntity = SirenModel.SirenEmbeddedLink(
                arrayOf("States", "Collection"),
                arrayOf("/rels/project-available-states"),
                "/projects/${entity.name}/states")

        val transitionsEntity = SirenModel.SirenEmbeddedLink(
                arrayOf("Transitions", "Collection"),
                arrayOf("/rels/project-state-transitions"),
                "/projects/${entity.name}/state-transitions")

        val issuesEntity = SirenModel.SirenEmbeddedLink(
                arrayOf("Issues", "Collection"),
                arrayOf("/rels/project-issues"),
                "/issues")

        return arrayOf(
                labelsEntity,
                statesEntity,
                transitionsEntity,
                issuesEntity)
    }

    override fun getActions(): Array<SirenModel.SirenAction>? {
        val updateProjectAction = SirenModel.SirenAction(
                name = "update-project",
                title = "Update Project",
                method = HttpMethod.PUT.name,
                href = "/projects/${entity.name}",
                type = "application/json",
                fields = UpdateProjectInput.getSirenActionFields()
        )

        val deleteProjectAction = SirenModel.SirenAction(
                name = "delete-project",
                title = "Delete Project",
                method = HttpMethod.DELETE.name,
                href = "/projects/${entity.name}"
        )

        return arrayOf(updateProjectAction, deleteProjectAction)
    }

    override fun getLinks(): Array<SirenModel.SirenLink>? {
        return arrayOf(SirenModel.SirenLink(arrayOf("self"), "/projects/${entity.name}"))
    }
}