package pt.isel.daw.g8.projectmanager.model.outputModel.errorRepresentations

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import pt.isel.daw.g8.projectmanager.model.outputModel.hypermedia.ProblemJson

@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadRequestException : HttpErrorException() {
    override fun toProblemJson(): ProblemJson {
        return ProblemJson(
                type = "/bad-request",
                title = HttpStatus.BAD_REQUEST.reasonPhrase,
                status = HttpStatus.BAD_REQUEST.value(),
                detail = "Request is not formed properly!")
    }
}