package pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations

import org.springframework.http.HttpMethod
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.model.inputModel.IssueLabelInput
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.IssueLabelCollectionOutput
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.IssueLabelOutput

class IssueLabelCollectionSiren(override val entity : IssueLabelCollectionOutput) : SirenRepresentation {
    override fun getClasses(): Array<String>? = arrayOf("Issue Labels", "Collection")

    override fun getProperties(): EntityRepresentation? = entity

    override fun getEntities(): Array<SirenModel.SirenEntity>? {
        val issueLabels = entity.issueLabels
        return Array(issueLabels.size) {index ->
            val issueLabel = issueLabels[index]
            SirenModel.SirenEmbeddedRepresentation(
                    _class = arrayOf("Issue Label"),
                    rel = arrayOf("/rels/issue-label"),
                    properties = IssueLabelOutput(issueLabel)
            )
        }
    }

    override fun getActions(): Array<SirenModel.SirenAction>? {
        val addIssueLabelAction = SirenModel.SirenAction(
                name = "add-issue-label",
                title = "Add Issue Label",
                method = HttpMethod.POST.name,
                href = ProjectPaths.ISSUE_LABELS,
                type = "application/json",
                fields = IssueLabelInput.getSirenActionFields()
        )

        return arrayOf(addIssueLabelAction)
    }

    override fun getLinks(): Array<SirenModel.SirenLink>? {
        val selfLink = SirenModel.SirenLink(arrayOf("self"), "${ProjectPaths.ISSUE_LABELS}?${ProjectPaths.ISSUE_ID_VAR}=${entity.issueId}")
        return arrayOf(selfLink)
    }
}