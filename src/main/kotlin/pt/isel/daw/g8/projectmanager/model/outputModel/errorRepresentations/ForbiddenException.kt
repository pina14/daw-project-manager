package pt.isel.daw.g8.projectmanager.model.outputModel.errorRepresentations

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import pt.isel.daw.g8.projectmanager.model.outputModel.hypermedia.ProblemJson

@ResponseStatus(HttpStatus.FORBIDDEN)
class ForbiddenException : HttpErrorException() {
    override fun toProblemJson(): ProblemJson {
        return ProblemJson(
                type = "/forbidden-request",
                title = HttpStatus.FORBIDDEN.reasonPhrase,
                status = HttpStatus.FORBIDDEN.value(),
                detail = "Resource forbidden to this level of authentication!")
    }
}