package pt.isel.daw.g8.projectmanager.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.repository.UserInfoRepo

@RestController
@RequestMapping(ProjectPaths.USERS)
class UserInfoController {

    @Autowired
    lateinit var userRepo : UserInfoRepo

    @PostMapping
    fun createUser() {
     //TODO Implement and set parameters
    }

    @PutMapping(ProjectPaths.USER_ID)
    fun updateUser() {
        //TODO Implement and set parameters
    }

    @DeleteMapping(ProjectPaths.USER_ID)
    fun deleteUser(@PathVariable(ProjectPaths.USERNAME_VAR) username: String) {
        //TODO Implement and set parameters
    }
}
