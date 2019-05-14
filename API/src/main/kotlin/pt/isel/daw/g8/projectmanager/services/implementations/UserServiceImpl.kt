package pt.isel.daw.g8.projectmanager.services.implementations

import org.springframework.http.HttpStatus
import pt.isel.daw.g8.projectmanager.model.databaseModel.User
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.BadRequestException
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.ConflictException
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.NotFoundException
import pt.isel.daw.g8.projectmanager.model.inputModel.CreateUserInput
import pt.isel.daw.g8.projectmanager.model.inputModel.UpdateUserInput
import pt.isel.daw.g8.projectmanager.model.outputModel.EmptyResponseEntity
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.UserOutput
import pt.isel.daw.g8.projectmanager.repository.UserRepo
import pt.isel.daw.g8.projectmanager.services.interfaces.UserService

class UserServiceImpl(private val userRepo : UserRepo) : UserService {
    override fun createUser(user: CreateUserInput): EmptyResponseEntity {
        if(userRepo.existsById(user.username))
            throw ConflictException("There's already one user with this username!")
        if(user.username.contains(":") || user.username.contains(" "))
            throw BadRequestException("username can't contain ':' character or spaces.")

        val dbUser = User(user.username, user.password, user.email, user.fullName)

        userRepo.save(dbUser)
        return EmptyResponseEntity(HttpStatus.CREATED)
    }

    override fun getUserByUsername(username: String): OutputModel {
        val user = userRepo.findById(username)
        if(!user.isPresent)
            throw NotFoundException("User with username '$username' doesn't exist.")

        return UserOutput(user.get()).toSiren()
    }

    override fun updateUser(username : String, user: UpdateUserInput): EmptyResponseEntity {
        val oldUser = userRepo.findById(username).get()
        val dbUser = User(oldUser.username, oldUser.password, user.email, user.fullName)
        userRepo.save(dbUser)
        return EmptyResponseEntity(HttpStatus.OK)
    }

    override fun deleteUser(username: String): EmptyResponseEntity {
        if(!userRepo.existsById(username))
            throw NotFoundException("User with username '$username' doesn't exist.")

        userRepo.deleteById(username)
        return EmptyResponseEntity(HttpStatus.OK)
    }
}