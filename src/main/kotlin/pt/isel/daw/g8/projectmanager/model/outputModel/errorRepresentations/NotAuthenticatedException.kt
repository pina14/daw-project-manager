package pt.isel.daw.g8.projectmanager.model.outputModel.errorRepresentations

import org.springframework.http.HttpStatus
import pt.isel.daw.g8.projectmanager.model.outputModel.mediaType.ProblemJson

class NotAuthenticatedException() : HttpErrorException() {

    override var errorMessage = "Authentication is needed to this resource."

    constructor(customMessage : String) : this() {
        errorMessage = customMessage
    }

    override fun toProblemJson(): ProblemJson {
        return ProblemJson(
                type = "/not-authenticated-request",
                title = HttpStatus.UNAUTHORIZED.reasonPhrase,
                status = HttpStatus.UNAUTHORIZED.value(),
                detail = errorMessage)
    }
}