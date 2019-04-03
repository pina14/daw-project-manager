package pt.isel.daw.g8.projectmanager.model.inputModel

import pt.isel.daw.g8.projectmanager.model.databaseModel.UserInfo

class UserInfoInput(val username : String,
                    val password : String,
                    val email : String,
                    val fullName : String) : InputModel {
    override fun toDbModel(): UserInfo = UserInfo(username, password, email, fullName)
}