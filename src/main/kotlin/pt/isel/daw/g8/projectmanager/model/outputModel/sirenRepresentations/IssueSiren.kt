package pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations

import org.springframework.http.HttpMethod
import pt.isel.daw.g8.projectmanager.model.inputModel.UpdateIssueInput
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.IssueOutput

class IssueSiren(override val entity : IssueOutput) : SirenRepresentation {
    override fun getClasses(): Array<String>? = arrayOf("Issue")

    override fun getProperties(): EntityRepresentation? = entity

    override fun getEntities(): Array<SirenModel.SirenEntity>? {
        val commentsEntity = SirenModel.SirenEmbeddedLink(
                arrayOf("Comments", "Collection"),
                arrayOf("/rels/issue-comments"),
                "/issues/${entity.id}/comments")

        val labelsEntity = SirenModel.SirenEmbeddedLink(
                arrayOf("Labels", "Collection"),
                arrayOf("/rels/issue-labels"),
                "/issues/${entity.id}/labels")

        return arrayOf(commentsEntity, labelsEntity)
    }

    override fun getActions(): Array<SirenModel.SirenAction>? {
        val updateIssueAction = SirenModel.SirenAction(
                name = "update-issue",
                title = "Update Issue",
                method = HttpMethod.PUT.name,
                href = "/issues/${entity.id}",
                type = "application/json",
                fields = UpdateIssueInput.getSirenActionFields()
        )

        val deleteIssueAction = SirenModel.SirenAction(
                name = "delete-issue",
                title = "Delete Issue",
                method = HttpMethod.DELETE.name,
                href = "/issues/${entity.id}"
        )

        return arrayOf(updateIssueAction, deleteIssueAction)
    }

    override fun getLinks(): Array<SirenModel.SirenLink>? {
        return arrayOf(SirenModel.SirenLink(arrayOf("self"), "/issues/${entity.id}"))
    }
}