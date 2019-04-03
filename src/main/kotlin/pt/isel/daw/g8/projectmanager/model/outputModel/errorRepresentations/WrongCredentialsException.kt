package pt.isel.daw.g8.projectmanager.model.outputModel.errorRepresentations

import org.springframework.http.HttpStatus
import pt.isel.daw.g8.projectmanager.model.outputModel.mediaType.ProblemJson

class WrongCredentialsException() : HttpErrorException() {

    override var errorMessage = "Wrong credentials in authentication."

    constructor(customMessage : String) : this() {
        errorMessage = customMessage
    }

    override fun toProblemJson(): ProblemJson {
        return ProblemJson(
                type = "/not-found-request",
                title = HttpStatus.UNAUTHORIZED.reasonPhrase,
                status = HttpStatus.UNAUTHORIZED.value(),
                detail = errorMessage)
    }
}