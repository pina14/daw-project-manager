package pt.isel.daw.g8.projectmanager.services.interfaces

import org.springframework.http.ResponseEntity
import pt.isel.daw.g8.projectmanager.model.inputModel.UserInfoInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel

interface UserInfoService {
    fun createUser(user: UserInfoInput): ResponseEntity<Unit>
    fun getUserByUsername(username: String): OutputModel
    fun updateUser(user: UserInfoInput): ResponseEntity<Unit>
    fun deleteUser(username: String): ResponseEntity<Unit>
}