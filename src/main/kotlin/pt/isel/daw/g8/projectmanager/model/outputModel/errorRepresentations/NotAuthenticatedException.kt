package pt.isel.daw.g8.projectmanager.model.outputModel.errorRepresentations

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import pt.isel.daw.g8.projectmanager.model.outputModel.hypermedia.ProblemJson

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class NotAuthenticatedException : HttpErrorException() {
    override fun toProblemJson(): ProblemJson {
        return ProblemJson(
                type = "/not-authenticated-request",
                title = HttpStatus.UNAUTHORIZED.reasonPhrase,
                status = HttpStatus.UNAUTHORIZED.value(),
                detail = "Authentication is needed to this resource!")
    }
}