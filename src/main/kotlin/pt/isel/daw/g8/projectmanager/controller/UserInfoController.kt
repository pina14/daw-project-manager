package pt.isel.daw.g8.projectmanager.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.middleware.RequiresAuthentication
import pt.isel.daw.g8.projectmanager.model.inputModel.UserInfoInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.errorRepresentations.ForbiddenException
import pt.isel.daw.g8.projectmanager.model.outputModel.mediaType.SirenModel
import pt.isel.daw.g8.projectmanager.services.interfaces.UserInfoService
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(ProjectPaths.USERS)
class UserInfoController(val userService : UserInfoService) {

    @PostMapping(consumes = ["application/json"])
    fun createUser(@RequestBody user : UserInfoInput)
            : ResponseEntity<Unit> = userService.createUser(user)

    @GetMapping(ProjectPaths.USER_ID, produces = [SirenModel.mediaType])
    fun getUserByUsername(@PathVariable(ProjectPaths.USERNAME_VAR) username: String)
            : OutputModel = userService.getUserByUsername(username)

    @PutMapping(ProjectPaths.USER_ID, consumes = ["application/json"])
    @RequiresAuthentication
    fun updateUser(request: HttpServletRequest,
                   @RequestBody user : UserInfoInput,
                   @PathVariable(ProjectPaths.USERNAME_VAR) username: String) : ResponseEntity<Unit> {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR)
        if(authUsername != user.username || authUsername != username)
            throw ForbiddenException("Authentication credentials are not valid to make changes to this user!")

        return userService.updateUser(user)
    }

    @DeleteMapping(ProjectPaths.USER_ID)
    @RequiresAuthentication
    fun deleteUser(request: HttpServletRequest,
                   @PathVariable(ProjectPaths.USERNAME_VAR) username: String) : ResponseEntity<Unit> {
        val authUsername = request.getAttribute(ProjectPaths.USERNAME_VAR)
        if(authUsername != username)
            throw ForbiddenException("Authentication credentials are not valid to make changes to this user!")

        return userService.deleteUser(username)
    }
}
