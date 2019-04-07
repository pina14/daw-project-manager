package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.User
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations.UserSiren

class UserOutput(user : User) : EntityRepresentation {
    var username : String? = null
    var email : String? = null
    var fullName : String? = null

    init {
        username = user.username
        email = user.email
        fullName = user.fullName
    }

    override fun toSiren(): SirenModel = SirenModel(UserSiren(this))
}