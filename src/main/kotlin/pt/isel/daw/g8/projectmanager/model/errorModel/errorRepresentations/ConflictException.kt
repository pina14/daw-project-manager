package pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations

import org.springframework.http.HttpStatus
import pt.isel.daw.g8.projectmanager.model.errorModel.ProblemJson

class ConflictException() : HttpErrorException() {
    override var errorMessage = "Repeated entity."

    constructor(customMessage : String) : this() {
        errorMessage = customMessage
    }

    override fun toProblemJson(): ProblemJson {
        return ProblemJson(
                type = "/duplicate-conflict-request",
                title = HttpStatus.CONFLICT.reasonPhrase,
                status = HttpStatus.CONFLICT.value(),
                detail = errorMessage)
    }
}