package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.UserInfo
import pt.isel.daw.g8.projectmanager.model.outputModel.hypermedia.SirenModel

class UserInfoOutput(user : UserInfo) : EntityRepresentation {
    var username : String? = null

    init {
        username = user.username
    }

    //TODO change links parameter
    override fun toSiren(): SirenModel = SirenModel(
            properties = this,
            _class = arrayOf("User Info"),
            links = arrayOf(SirenModel.SirenLink(arrayOf("self"), "http://localhost:8080/users/$username"))
    )
}