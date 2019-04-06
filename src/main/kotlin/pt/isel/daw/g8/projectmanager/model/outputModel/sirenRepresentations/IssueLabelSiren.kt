package pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations

import org.springframework.http.HttpMethod
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.IssueLabelOutput

class IssueLabelSiren(override val entity : IssueLabelOutput) : SirenRepresentation {
    override fun getClasses(): Array<String>? = arrayOf("Issue Label")

    override fun getProperties(): EntityRepresentation? = entity

    override fun getEntities(): Array<SirenModel.SirenEntity>? = null

    override fun getActions(): Array<SirenModel.SirenAction>? {
        val deleteIssueLabelAction = SirenModel.SirenAction(
                name = "delete-issue-label",
                title = "Delete Issue Label",
                method = HttpMethod.DELETE.name,
                href = "${ProjectPaths.ISSUE_LABELS}?${ProjectPaths.ISSUE_ID_VAR}=${entity.issueId}&${ProjectPaths.LABEL_NAME_VAR}=${entity.labelName}"
        )

        return arrayOf(deleteIssueLabelAction)
    }

    override fun getLinks(): Array<SirenModel.SirenLink>? = null
}
