package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.UserInfo
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations.UserInfoSiren

class UserInfoOutput(user : UserInfo) : EntityRepresentation {
    var username : String? = null
    var email : String? = null
    var fullName : String? = null

    init {
        username = user.username
        email = user.email
        fullName = user.fullName
    }

    override fun toSiren(): SirenModel = SirenModel(UserInfoSiren(this))
}