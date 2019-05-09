package pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations

import org.springframework.http.HttpMethod
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.model.inputModel.UpdateProjectInput
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.ProjectOutput

class ProjectSiren(override val entity : ProjectOutput) : SirenRepresentation {
    override fun getClasses(): Array<String>? = arrayOf("Project")

    override fun getProperties(): EntityRepresentation? = entity

    override fun getEntities(): Array<SirenModel.SirenEntity>? {
        val labelsEntity = SirenModel.SirenEmbeddedLink(
                arrayOf("Collection", "Label"),
                arrayOf("/rels/project-available-labels"),
                "${ProjectPaths.PROJECT_LABELS}?${ProjectPaths.PROJECT_NAME_VAR}=${entity.name}")

        val statesEntity = SirenModel.SirenEmbeddedLink(
                arrayOf("Collection", "State"),
                arrayOf("/rels/project-available-states"),
                "${ProjectPaths.PROJECT_STATES}?${ProjectPaths.PROJECT_NAME_VAR}=${entity.name}")

        val transitionsEntity = SirenModel.SirenEmbeddedLink(
                arrayOf("Collection", "Transition"),
                arrayOf("/rels/project-state-transitions"),
                "${ProjectPaths.PROJECT_STATE_TRANSITIONS}?${ProjectPaths.PROJECT_NAME_VAR}=${entity.name}")

        val issuesEntity = SirenModel.SirenEmbeddedLink(
                arrayOf("Collection", "Issue"),
                arrayOf("/rels/project-issues"),
                "${ProjectPaths.ISSUES}?${ProjectPaths.PROJECT_NAME_VAR}=${entity.name}")

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
                href = "${ProjectPaths.PROJECTS}/${entity.name}",
                type = "application/json",
                fields = UpdateProjectInput.getSirenActionFields()
        )

        val deleteProjectAction = SirenModel.SirenAction(
                name = "delete-project",
                title = "Delete Project",
                method = HttpMethod.DELETE.name,
                href = "${ProjectPaths.PROJECTS}/${entity.name}"
        )

        return arrayOf(updateProjectAction, deleteProjectAction)
    }

    override fun getLinks(): Array<SirenModel.SirenLink>? {
        return arrayOf(SirenModel.SirenLink(arrayOf("self"), "${ProjectPaths.PROJECTS}/${entity.name}"))
    }
}