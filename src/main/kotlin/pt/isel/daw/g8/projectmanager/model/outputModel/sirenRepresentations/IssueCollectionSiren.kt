package pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations

import org.springframework.http.HttpMethod
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.model.inputModel.CreateIssueInput
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.IssueCollectionOutput
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.IssueOutput

class IssueCollectionSiren(override val entity : IssueCollectionOutput) : SirenRepresentation {
    override fun getClasses(): Array<String>? = arrayOf("Issues", "Collection")

    override fun getProperties(): EntityRepresentation? = entity

    override fun getEntities(): Array<SirenModel.SirenEntity>? {
        val issues = entity.issues
        return Array(issues.size) {index ->
            val issue = issues[index]
            SirenModel.SirenEmbeddedRepresentation(
                    _class = arrayOf("Issue"),
                    rel = arrayOf("/rels/issue"),
                    properties = IssueOutput(issue),
                    links = arrayOf(SirenModel.SirenLink(arrayOf("self"), "${ProjectPaths.ISSUES}?${ProjectPaths.ISSUE_ID_VAR}=${issue.id}"))
            )
        }
    }

    override fun getActions(): Array<SirenModel.SirenAction>? {
        val createIssueAction = SirenModel.SirenAction(
                name = "create-issue",
                title = "Create Issue",
                method = HttpMethod.POST.name,
                href = ProjectPaths.ISSUES,
                type = "application/json",
                fields = CreateIssueInput.getSirenActionFields()
        )

        return arrayOf(createIssueAction)
    }

    override fun getLinks(): Array<SirenModel.SirenLink>? {
        val selfLink = SirenModel.SirenLink(arrayOf("self"), "${ProjectPaths.ISSUES}?${ProjectPaths.PROJECT_NAME_VAR}=${entity.projectName}")
        return arrayOf(selfLink)
    }
}