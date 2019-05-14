package pt.isel.daw.g8.projectmanager.services.interfaces

import pt.isel.daw.g8.projectmanager.model.inputModel.CreateUserInput
import pt.isel.daw.g8.projectmanager.model.inputModel.UpdateUserInput
import pt.isel.daw.g8.projectmanager.model.outputModel.EmptyResponseEntity
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel

interface UserService {
    fun createUser(user: CreateUserInput): EmptyResponseEntity
    fun getUserByUsername(username: String): OutputModel
    fun updateUser(username : String, user: UpdateUserInput): EmptyResponseEntity
    fun deleteUser(username: String): EmptyResponseEntity
}