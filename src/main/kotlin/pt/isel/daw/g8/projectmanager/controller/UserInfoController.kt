package pt.isel.daw.g8.projectmanager.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pt.isel.daw.g8.projectmanager.loggerFor
import pt.isel.daw.g8.projectmanager.repository.UserInfoRepo

private val log = loggerFor<UserInfoController>()

@RestController
@RequestMapping("/users")
class UserInfoController {

    @Autowired
    lateinit var userRepo : UserInfoRepo

    @GetMapping("/{username}")
    fun getUserById(@PathVariable("username") username: String)
            = userRepo
            .findById(username)
            .get()
            .buildOutputModel()
            .toSirenRepr()
}
