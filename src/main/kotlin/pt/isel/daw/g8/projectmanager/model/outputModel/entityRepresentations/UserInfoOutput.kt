package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.UserInfo
import pt.isel.daw.g8.projectmanager.model.outputModel.mediaType.SirenModel

class UserInfoOutput(user : UserInfo) : EntityRepresentation {
    var username : String? = null
    var email : String? = null
    var fullName : String? = null

    init {
        username = user.username
        email = user.email
        fullName = user.fullName
    }

    //TODO change links parameter
    override fun toSiren(): SirenModel = SirenModel(
            properties = this,
            _class = arrayOf("User Info"),
            links = arrayOf(SirenModel.SirenLink(arrayOf("self"), "/users/$username"))
    )
}