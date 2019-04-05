package pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations

import org.springframework.http.HttpStatus
import pt.isel.daw.g8.projectmanager.model.errorModel.ProblemJson

class WrongCredentialsException() : HttpErrorException() {

    override var errorMessage = "Wrong credentials in authentication."

    constructor(customMessage : String) : this() {
        errorMessage = customMessage
    }

    override fun toProblemJson(): ProblemJson {
        return ProblemJson(
                type = "/wrong-credentials",
                title = HttpStatus.UNAUTHORIZED.reasonPhrase,
                status = HttpStatus.UNAUTHORIZED.value(),
                detail = errorMessage)
    }
}