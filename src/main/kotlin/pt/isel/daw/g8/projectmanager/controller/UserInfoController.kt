package pt.isel.daw.g8.projectmanager.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.middleware.RequiresAuthentication
import pt.isel.daw.g8.projectmanager.model.inputModel.UserInfoInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.errorRepresentations.ConflictException
import pt.isel.daw.g8.projectmanager.model.outputModel.errorRepresentations.ForbiddenException
import pt.isel.daw.g8.projectmanager.model.outputModel.errorRepresentations.NotFoundException
import pt.isel.daw.g8.projectmanager.model.outputModel.mediaType.SirenModel
import pt.isel.daw.g8.projectmanager.repository.UserInfoRepo
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(ProjectPaths.USERS)
class UserInfoController {

    @Autowired
    lateinit var userRepo : UserInfoRepo

    @PostMapping(consumes = ["application/json"])
    fun createUser(@RequestBody user : UserInfoInput) : ResponseEntity<Unit> {
        if(userRepo.existsById(user.username))
            throw ConflictException("There's already one user with this username!")

        val dbUser = user.toDbModel()

        userRepo.save(dbUser)
        return ResponseEntity(HttpStatus.CREATED)
    }

    //TODO decide if output returns password or not
    //TODO decide if this is an authenticated request
    @GetMapping(ProjectPaths.USER_ID, produces = [SirenModel.mediaType])
    @RequiresAuthentication
    fun getUserByUsername(@PathVariable(ProjectPaths.USERNAME_VAR) username: String) : OutputModel {
        val user = userRepo.findById(username)
        if(!user.isPresent)
            throw NotFoundException()

        return user.get().buildEntityRepresentation().toSiren()
    }


    @PutMapping(ProjectPaths.USER_ID, consumes = ["application/json"])
    @RequiresAuthentication
    fun updateUser(request: HttpServletRequest,
                   @RequestBody user : UserInfoInput,
                   @PathVariable(ProjectPaths.USERNAME_VAR) username: String) : ResponseEntity<Unit> {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR)
        if(authUsername != user.username || authUsername != username)
            throw ForbiddenException("Authentication credentials are not valid to make changes to this user!")

        val dbUser = user.toDbModel()
        userRepo.save(dbUser)
        return ResponseEntity(HttpStatus.OK)
    }

    @DeleteMapping(ProjectPaths.USER_ID)
    @RequiresAuthentication
    fun deleteUser(request: HttpServletRequest,
                   @PathVariable(ProjectPaths.USERNAME_VAR) username: String) : ResponseEntity<Unit> {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR)
        if(authUsername != username)
            throw ForbiddenException("Authentication credentials are not valid to make changes to this user!")

        userRepo.deleteById(username)
        return ResponseEntity(HttpStatus.OK)
    }
}
