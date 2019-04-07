package pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations

import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.IssueCommentOutput

class IssueCommentSiren(override val entity : IssueCommentOutput) : SirenRepresentation {
    override fun getClasses(): Array<String>? = arrayOf("Issue Comment")

    override fun getProperties(): EntityRepresentation? = entity

    override fun getEntities(): Array<SirenModel.SirenEntity>? = null

    override fun getActions(): Array<SirenModel.SirenAction>? = null

    override fun getLinks(): Array<SirenModel.SirenLink>? {
        return arrayOf(SirenModel.SirenLink(arrayOf("self"), "${ProjectPaths.ISSUE_COMMENTS}/${entity.id}"))
    }
}
