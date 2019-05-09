package pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations

import org.springframework.http.HttpMethod
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.ProjectStateTransitionOutput

class ProjectStateTransitionSiren(override val entity : ProjectStateTransitionOutput) : SirenRepresentation {
    override fun getClasses(): Array<String>? = arrayOf("Project State Transition")

    override fun getProperties(): EntityRepresentation? = entity

    override fun getEntities(): Array<SirenModel.SirenEntity>? = null

    override fun getActions(): Array<SirenModel.SirenAction>? {
        val deleteProjectStateTransitionAction = SirenModel.SirenAction(
                name = "delete-project-state-transition",
                title = "Delete Project State Transition",
                method = HttpMethod.DELETE.name,
                href = "${ProjectPaths.PROJECT_STATE_TRANSITIONS}?" +
                        "${ProjectPaths.PROJECT_NAME_VAR}=${entity.projectName}&" +
                        "${ProjectPaths.FROM_STATE_VAR}=${entity.fromState}&" +
                        "${ProjectPaths.TO_STATE_VAR}=${entity.toState}"
        )

        return arrayOf(deleteProjectStateTransitionAction)
    }

    override fun getLinks(): Array<SirenModel.SirenLink>? {
        return arrayOf(SirenModel.SirenLink(arrayOf("self"),
                "${ProjectPaths.PROJECT_STATE_TRANSITIONS}?" +
                        "${ProjectPaths.PROJECT_NAME_VAR}=${entity.projectName}&" +
                        "${ProjectPaths.FROM_STATE_VAR}=${entity.fromState}&" +
                        "${ProjectPaths.TO_STATE_VAR}=${entity.toState}"))
    }
}
