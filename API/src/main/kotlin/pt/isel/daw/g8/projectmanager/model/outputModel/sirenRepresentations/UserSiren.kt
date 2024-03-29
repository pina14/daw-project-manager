package pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations

import org.springframework.http.HttpMethod
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.model.inputModel.UpdateUserInput
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.UserOutput

class UserSiren(override val entity : UserOutput) : SirenRepresentation {
    override fun getClasses(): Array<String>? = arrayOf("User")

    override fun getProperties(): EntityRepresentation? = entity

    override fun getEntities(): Array<SirenModel.SirenEntity>? {
        val projectsEntity = SirenModel.SirenEmbeddedLink(
                arrayOf("Collection", "Project"),
                arrayOf("/rels/user-projects"),
                "${ProjectPaths.PROJECTS}?${ProjectPaths.USERNAME_VAR}=${entity.username}"
        )

        return arrayOf(projectsEntity)
    }

    override fun getActions(): Array<SirenModel.SirenAction>?  {
        val updateUserAction = SirenModel.SirenAction(
                name = "update-user",
                title = "Update User",
                method = HttpMethod.PUT.name,
                href = "${ProjectPaths.USERS}/${entity.username}",
                type = "application/json",
                fields = UpdateUserInput.getSirenActionFields()
        )

        val deleteUserAction = SirenModel.SirenAction(
                name = "delete-user",
                title = "Delete User",
                method = HttpMethod.DELETE.name,
                href = "${ProjectPaths.USERS}/${entity.username}"
        )

        return arrayOf(updateUserAction, deleteUserAction)
    }

    override fun getLinks(): Array<SirenModel.SirenLink>? {
        return arrayOf(SirenModel.SirenLink(arrayOf("self"), "${ProjectPaths.USERS}/${entity.username}"))
    }
}