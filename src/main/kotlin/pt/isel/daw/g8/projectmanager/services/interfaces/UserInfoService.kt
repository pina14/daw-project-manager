package pt.isel.daw.g8.projectmanager.services.interfaces

import org.springframework.http.ResponseEntity
import pt.isel.daw.g8.projectmanager.model.inputModel.CreateUserInfoInput
import pt.isel.daw.g8.projectmanager.model.inputModel.UpdateUserInfoInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel

interface UserInfoService {
    fun createUser(user: CreateUserInfoInput): ResponseEntity<Unit>
    fun getUserByUsername(username: String): OutputModel
    fun updateUser(username : String, user: UpdateUserInfoInput): ResponseEntity<Unit>
    fun deleteUser(username: String): ResponseEntity<Unit>
}