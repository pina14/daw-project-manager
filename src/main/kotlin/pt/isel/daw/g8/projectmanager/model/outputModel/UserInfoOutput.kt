package pt.isel.daw.g8.projectmanager.model.outputModel

import pt.isel.daw.g8.projectmanager.model.databaseModel.UserInfo
import pt.isel.daw.g8.projectmanager.model.mediaModel.SirenModel

class UserInfoOutput(user : UserInfo) : OutputModel {
    var username : String? = null

    init {
        username = user.username
    }

    override fun toSirenRepr(): SirenModel = SirenModel(properties = this)
}