package pt.isel.daw.g8.projectmanager.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.middleware.RequiresAuthentication
import pt.isel.daw.g8.projectmanager.model.inputModel.UserInfoInput
import pt.isel.daw.g8.projectmanager.model.outputModel.errorRepresentations.ConflictException
import pt.isel.daw.g8.projectmanager.repository.UserInfoRepo

@RestController
@RequestMapping(ProjectPaths.USERS)
class UserInfoController {

    @Autowired
    lateinit var userRepo : UserInfoRepo

    @PostMapping(consumes = ["application/json"])
    fun createUser(@RequestBody user : UserInfoInput) : ResponseEntity<Unit> {
        if(userRepo.existsById(user.username))
            throw ConflictException()

        val dbUser = user.toDbModel()

        userRepo.save(dbUser)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @GetMapping(ProjectPaths.USER_ID)
    @RequiresAuthentication
    fun getUserByUsername(@PathVariable(ProjectPaths.USERNAME_VAR) username: String) {
        //TODO Implement and set parameters
    }


    @PutMapping(ProjectPaths.USER_ID, consumes = ["application/json"])
    @RequiresAuthentication
    fun updateUser() {
        //TODO Implement and set parameters
    }

    @DeleteMapping(ProjectPaths.USER_ID)
    @RequiresAuthentication
    fun deleteUser(@PathVariable(ProjectPaths.USERNAME_VAR) username: String) {
        //TODO Implement and set parameters
    }
}
