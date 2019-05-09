package pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations

import org.springframework.http.HttpMethod
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.ProjectAvailableLabelOutput

class ProjectAvailableLabelSiren(override val entity : ProjectAvailableLabelOutput) : SirenRepresentation {
    override fun getClasses(): Array<String>? = arrayOf("Project Available Label")

    override fun getProperties(): EntityRepresentation? = entity

    override fun getEntities(): Array<SirenModel.SirenEntity>? = null

    override fun getActions(): Array<SirenModel.SirenAction>? {
        val deleteProjectAvailableLabelAction = SirenModel.SirenAction(
                name = "delete-project-available-label",
                title = "Delete Project Available Label",
                method = HttpMethod.DELETE.name,
                href = "${ProjectPaths.PROJECT_LABELS}?${ProjectPaths.PROJECT_NAME_VAR}=${entity.projectName}&${ProjectPaths.LABEL_NAME_VAR}=${entity.labelName}"
        )

        return arrayOf(deleteProjectAvailableLabelAction)
    }

    override fun getLinks(): Array<SirenModel.SirenLink>? {
        return arrayOf(SirenModel.SirenLink(arrayOf("self"),
                "${ProjectPaths.PROJECT_LABELS}?" +
                        "${ProjectPaths.PROJECT_NAME_VAR}=${entity.projectName}&" +
                        "${ProjectPaths.LABEL_NAME_VAR}=${entity.labelName}"))
    }
}
