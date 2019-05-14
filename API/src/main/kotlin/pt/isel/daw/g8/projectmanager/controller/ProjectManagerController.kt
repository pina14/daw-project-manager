package pt.isel.daw.g8.projectmanager.controller

import org.springframework.web.bind.annotation.CrossOrigin
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.ForbiddenException

@CrossOrigin
interface ProjectManagerController {

    fun checkAuthorizationToResource(vararg usernames: String?) {
        if(usernames.contains(null) || usernames.distinct().size > 1)
            throw ForbiddenException("Authentication credentials are not valid to make changes to this resource!")
    }
}