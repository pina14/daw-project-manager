package pt.isel.daw.g8.projectmanager.controller

import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.ForbiddenException

interface ProjectManagerController {

    fun checkAuthorizationToResource(authUsername : String?, resourceUsername : String) {
        if(authUsername == null || authUsername != resourceUsername)
            throw ForbiddenException("Authentication credentials are not valid to make changes to this resource!")
    }
}