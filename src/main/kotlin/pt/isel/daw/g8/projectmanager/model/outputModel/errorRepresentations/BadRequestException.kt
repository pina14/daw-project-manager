package pt.isel.daw.g8.projectmanager.model.outputModel.errorRepresentations

import org.springframework.http.HttpStatus
import pt.isel.daw.g8.projectmanager.model.outputModel.hypermedia.ProblemJson

class BadRequestException() : HttpErrorException() {
    override var errorMessage = "Request is not formed properly."

    constructor(customMessage : String) : this() {
        errorMessage = customMessage
    }

    override fun toProblemJson(): ProblemJson {
        return ProblemJson(
                type = "/bad-request",
                title = HttpStatus.BAD_REQUEST.reasonPhrase,
                status = HttpStatus.BAD_REQUEST.value(),
                detail = errorMessage)
    }
}