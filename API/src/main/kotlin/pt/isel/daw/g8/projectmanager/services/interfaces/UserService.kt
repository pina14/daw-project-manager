package pt.isel.daw.g8.projectmanager.services.interfaces

import org.springframework.http.ResponseEntity
import pt.isel.daw.g8.projectmanager.model.inputModel.CreateUserInput
import pt.isel.daw.g8.projectmanager.model.inputModel.UpdateUserInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel

interface UserService {
    fun createUser(user: CreateUserInput): ResponseEntity<Unit>
    fun getUserByUsername(username: String): OutputModel
    fun updateUser(username : String, user: UpdateUserInput): ResponseEntity<Unit>
    fun deleteUser(username: String): ResponseEntity<Unit>
}