package pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations

import org.springframework.http.HttpMethod
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.ProjectAvailableStateOutput

class ProjectAvailableStateSiren(override val entity : ProjectAvailableStateOutput) : SirenRepresentation {
    override fun getClasses(): Array<String>? = arrayOf("Project Available State")

    override fun getProperties(): EntityRepresentation? = entity

    override fun getEntities(): Array<SirenModel.SirenEntity>? = null

    override fun getActions(): Array<SirenModel.SirenAction>? {
        val deleteProjectAvailableStateAction = SirenModel.SirenAction(
                name = "delete-project-available-state",
                title = "Delete Project Available State",
                method = HttpMethod.DELETE.name,
                href = "${ProjectPaths.PROJECT_STATES}?${ProjectPaths.PROJECT_NAME_VAR}=${entity.projectName}&${ProjectPaths.STATE_NAME_VAR}=${entity.stateName}"
        )

        return arrayOf(deleteProjectAvailableStateAction)
    }

    override fun getLinks(): Array<SirenModel.SirenLink>? {
        return arrayOf(SirenModel.SirenLink(arrayOf("self"),
                "${ProjectPaths.PROJECT_STATES}?" +
                        "${ProjectPaths.PROJECT_NAME_VAR}=${entity.projectName}&" +
                        "${ProjectPaths.STATE_NAME_VAR}=${entity.stateName}"))
    }
}
