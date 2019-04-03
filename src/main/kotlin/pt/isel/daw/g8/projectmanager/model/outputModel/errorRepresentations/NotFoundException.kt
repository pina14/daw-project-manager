package pt.isel.daw.g8.projectmanager.model.outputModel.errorRepresentations

import org.springframework.http.HttpStatus
import pt.isel.daw.g8.projectmanager.model.outputModel.mediaType.ProblemJson

class NotFoundException() : HttpErrorException() {

    override var errorMessage = "This resource doesn't exists."

    constructor(customMessage : String) : this() {
        errorMessage = customMessage
    }

    override fun toProblemJson(): ProblemJson {
        return ProblemJson(
                type = "/not-found-request",
                title = HttpStatus.NOT_FOUND.reasonPhrase,
                status = HttpStatus.NOT_FOUND.value(),
                detail = errorMessage)
    }
}