package pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations

import org.springframework.http.HttpMethod
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.model.inputModel.IssueCommentInput
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.IssueCommentCollectionOutput
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.IssueCommentOutput

class IssueCommentCollectionSiren(override val entity : IssueCommentCollectionOutput) : SirenRepresentation {
    override fun getClasses(): Array<String>? = arrayOf("Collection", "Issue Comment")

    override fun getProperties(): EntityRepresentation? = null

    override fun getEntities(): Array<SirenModel.SirenEntity>? {
        val comments = entity.comments
        return Array(comments.size) {index ->
            val comment = comments[index]
            SirenModel.SirenEmbeddedRepresentation(
                    _class = arrayOf("Issue Comment"),
                    rel = arrayOf("/rels/issue-comment"),
                    properties = IssueCommentOutput(comment),
                    links = arrayOf(SirenModel.SirenLink(arrayOf("self"), "${ProjectPaths.ISSUE_COMMENTS}/${comment.id}"))
            )
        }
    }

    override fun getActions(): Array<SirenModel.SirenAction>? {
        val createCommentAction = SirenModel.SirenAction(
                name = "create-issue-comment",
                title = "Create Issue Comment",
                method = HttpMethod.POST.name,
                href = ProjectPaths.ISSUE_COMMENTS,
                type = "application/json",
                fields = IssueCommentInput.getSirenActionFields()
        )

        return arrayOf(createCommentAction)
    }

    override fun getLinks(): Array<SirenModel.SirenLink>? {
        val selfLink = SirenModel.SirenLink(arrayOf("self"), "${ProjectPaths.ISSUE_COMMENTS}?${ProjectPaths.ISSUE_ID_VAR}=${entity.issueId}")
        return arrayOf(selfLink)
    }
}