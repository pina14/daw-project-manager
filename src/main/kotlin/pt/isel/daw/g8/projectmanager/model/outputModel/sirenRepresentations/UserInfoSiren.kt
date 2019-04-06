package pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations

import org.springframework.http.HttpMethod
import pt.isel.daw.g8.projectmanager.model.inputModel.UpdateUserInfoInput
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.UserInfoOutput

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

    override fun getActions(): Array<SirenModel.SirenAction>?  {
        val updateUserAction = SirenModel.SirenAction(
                name = "update-user",
                title = "Update User",
                method = HttpMethod.PUT.name,
                href = "/users/${entity.username}",
                type = "application/json",
                fields = UpdateUserInfoInput.getSirenActionFields()
        )

        val deleteUserAction = SirenModel.SirenAction(
                name = "delete-user",
                title = "Delete User",
                method = HttpMethod.DELETE.name,
                href = "/users/${entity.username}"
        )

        return arrayOf(updateUserAction, deleteUserAction)
    }

    override fun getLinks(): Array<SirenModel.SirenLink>? {
        return arrayOf(SirenModel.SirenLink(arrayOf("self"), "/users/${entity.username}"))
    }
}