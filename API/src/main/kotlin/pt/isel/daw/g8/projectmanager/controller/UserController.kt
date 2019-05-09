package pt.isel.daw.g8.projectmanager.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.middleware.RequiresAuthentication
import pt.isel.daw.g8.projectmanager.model.inputModel.CreateUserInput
import pt.isel.daw.g8.projectmanager.model.inputModel.UpdateUserInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.services.interfaces.UserService
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(ProjectPaths.USERS)
class UserController(val userService : UserService) : ProjectManagerController {

    @PostMapping(consumes = ["application/json"])
    fun createUser(@RequestBody user : CreateUserInput)
            : ResponseEntity<Unit> = userService.createUser(user)

    @GetMapping(ProjectPaths.USER_ID, produces = [SirenModel.mediaType])
    fun getUserByUsername(@PathVariable(ProjectPaths.USERNAME_VAR) username: String)
            : OutputModel = userService.getUserByUsername(username)

    @PutMapping(ProjectPaths.USER_ID, consumes = ["application/json"])
    @RequiresAuthentication
    fun updateUser(request: HttpServletRequest,
                   @RequestBody user : UpdateUserInput,
                   @PathVariable(ProjectPaths.USERNAME_VAR) username: String) : ResponseEntity<Unit> {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR) as String?
        checkAuthorizationToResource(authUsername, username)

        return userService.updateUser(username, user)
    }

    @DeleteMapping(ProjectPaths.USER_ID)
    @RequiresAuthentication
    fun deleteUser(request: HttpServletRequest,
                   @PathVariable(ProjectPaths.USERNAME_VAR) username: String) : ResponseEntity<Unit> {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR) as String?
        checkAuthorizationToResource(authUsername, username)

        return userService.deleteUser(username)
    }
}
