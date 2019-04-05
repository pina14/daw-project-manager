package pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations

import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.UserInfoOutput
import pt.isel.daw.g8.projectmanager.model.outputModel.mediaType.SirenModel

class UserInfoSiren(override val entity : UserInfoOutput) : SirenRepresentation {
    override fun getClasses(): Array<String>? = arrayOf("User Info")

    override fun getProperties(): EntityRepresentation? = entity

    override fun getEntities(): Array<SirenModel.SirenEntity>? {
        val projectsEntity = SirenModel.SirenEmbeddedLink(
                arrayOf("Projects", "Collection"),
                arrayOf("/rels/user-projects"),
                "/users/${entity.username}/projects")

        return arrayOf(projectsEntity)
    }

    override fun getActions(): Array<SirenModel.SirenAction>? = null

    override fun getLinks(): Array<SirenModel.SirenLink>? {
        return arrayOf(SirenModel.SirenLink(arrayOf("self"), "/users/${entity.username}"))
    }
}