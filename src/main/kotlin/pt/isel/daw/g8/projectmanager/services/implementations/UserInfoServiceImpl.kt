package pt.isel.daw.g8.projectmanager.services.implementations

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import pt.isel.daw.g8.projectmanager.model.databaseModel.UserInfo
import pt.isel.daw.g8.projectmanager.model.inputModel.UserInfoInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.UserInfoOutput
import pt.isel.daw.g8.projectmanager.model.outputModel.errorRepresentations.ConflictException
import pt.isel.daw.g8.projectmanager.model.outputModel.errorRepresentations.NotFoundException
import pt.isel.daw.g8.projectmanager.repository.UserInfoRepo
import pt.isel.daw.g8.projectmanager.services.interfaces.UserInfoService

class UserInfoServiceImpl(val userRepo : UserInfoRepo) : UserInfoService {
    override fun createUser(user: UserInfoInput): ResponseEntity<Unit> {
        if(userRepo.existsById(user.username))
            throw ConflictException("There's already one user with this username!")

        val dbUser = UserInfo(user.username, user.password, user.email, user.fullName)

        userRepo.save(dbUser)
        return ResponseEntity(HttpStatus.CREATED)
    }

    override fun getUserByUsername(username: String): OutputModel {
        val user = userRepo.findById(username)
        if(!user.isPresent)
            throw NotFoundException()

        return UserInfoOutput(user.get()).toSiren()
    }

    override fun updateUser(user: UserInfoInput): ResponseEntity<Unit> {
        val dbUser = UserInfo(user.username, user.password, user.email, user.fullName)
        userRepo.save(dbUser)
        return ResponseEntity(HttpStatus.OK)
    }

    override fun deleteUser(username: String): ResponseEntity<Unit> {
        userRepo.deleteById(username)
        return ResponseEntity(HttpStatus.OK)
    }
}