package pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations

import org.springframework.http.HttpMethod
import pt.isel.daw.g8.projectmanager.model.inputModel.CommentInput
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.CommentCollectionOutput
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.CommentOutput
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation

class CommentCollectionSiren(override val entity : CommentCollectionOutput) : SirenRepresentation {
    override fun getClasses(): Array<String>? = arrayOf("Comments", "Collection")

    override fun getProperties(): EntityRepresentation? = entity

    override fun getEntities(): Array<SirenModel.SirenEntity>? {
        val comments = entity.comments
        return Array(comments.size) {index ->
            val comment = comments[index]
            SirenModel.SirenEmbeddedRepresentation(
                    _class = arrayOf("Comment"),
                    rel = arrayOf("/rels/issue-comment"),
                    properties = CommentOutput(comment),
                    links = arrayOf(SirenModel.SirenLink(arrayOf("self"), "/issues/${comment.issueId}/comments/${comment.id}"))
            )
        }
    }

    override fun getActions(): Array<SirenModel.SirenAction>? {
        val createCommentAction = SirenModel.SirenAction(
                name = "create-issue-comment",
                title = "Create Comment",
                method = HttpMethod.POST.name,
                href = "/issues/${entity.issueId}/comments",
                type = "application/json",
                fields = CommentInput.getSirenActionFields()
        )

        return arrayOf(createCommentAction)
    }

    override fun getLinks(): Array<SirenModel.SirenLink>? {
        val selfLink = SirenModel.SirenLink(arrayOf("self"), "/issues/${entity.issueId}/comments")
        return arrayOf(selfLink)
    }
}