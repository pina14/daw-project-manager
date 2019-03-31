package pt.isel.daw.g8.projectmanager.controller

import org.springframework.beans.factory.annotation.Autowired
import pt.isel.daw.g8.projectmanager.loggerFor
import org.springframework.web.bind.annotation.*
import pt.isel.daw.g8.projectmanager.repository.UserInfoRepo

private val log = loggerFor<UserInfoController>()

@RestController
@RequestMapping("/users")
class UserInfoController {

    @Autowired
    lateinit var userRepo : UserInfoRepo

    @GetMapping("/{username}")
    fun getUserById(@PathVariable("username") username: String) = userRepo.findById(username)
}
