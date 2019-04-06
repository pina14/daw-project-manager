package pt.isel.daw.g8.projectmanager.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.middleware.RequiresAuthentication
import pt.isel.daw.g8.projectmanager.model.inputModel.CreateUserInfoInput
import pt.isel.daw.g8.projectmanager.model.inputModel.UpdateUserInfoInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.services.interfaces.UserInfoService
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(ProjectPaths.USERS)
class UserInfoController(val userService : UserInfoService) : ProjectManagerController {

    @PostMapping(consumes = ["application/json"])
    fun createUser(@RequestBody user : CreateUserInfoInput)
            : ResponseEntity<Unit> = userService.createUser(user)

    @GetMapping(produces = [SirenModel.mediaType])
    fun getUserByUsername(@RequestParam(ProjectPaths.USERNAME_VAR) username: String)
            : OutputModel = userService.getUserByUsername(username)

    @PutMapping(consumes = ["application/json"])
    @RequiresAuthentication
    fun updateUser(request: HttpServletRequest,
                   @RequestBody user : UpdateUserInfoInput,
                   @RequestParam(ProjectPaths.USERNAME_VAR) username: String) : ResponseEntity<Unit> {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR) as String?
        checkAuthorizationToResource(authUsername, username)

        return userService.updateUser(username, user)
    }

    @DeleteMapping
    @RequiresAuthentication
    fun deleteUser(request: HttpServletRequest,
                   @RequestParam(ProjectPaths.USERNAME_VAR) username: String) : ResponseEntity<Unit> {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR) as String?
        checkAuthorizationToResource(authUsername, username)

        return userService.deleteUser(username)
    }
}
