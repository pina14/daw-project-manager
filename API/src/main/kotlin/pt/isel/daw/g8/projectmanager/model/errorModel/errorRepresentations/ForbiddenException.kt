package pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations

import org.springframework.http.HttpStatus
import pt.isel.daw.g8.projectmanager.model.errorModel.ProblemJson

class ForbiddenException() : HttpErrorException() {
    override var errorMessage = "Resource forbidden to this level of authentication."

    constructor(customMessage : String) : this() {
        errorMessage = customMessage
    }

    override fun toProblemJson(): ProblemJson {
        return ProblemJson(
                type = "/forbidden-request",
                title = HttpStatus.FORBIDDEN.reasonPhrase,
                status = HttpStatus.FORBIDDEN.value(),
                detail = errorMessage)
    }
}