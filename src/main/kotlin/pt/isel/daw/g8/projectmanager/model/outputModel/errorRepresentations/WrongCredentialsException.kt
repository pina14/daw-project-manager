package pt.isel.daw.g8.projectmanager.model.outputModel.errorRepresentations

import org.springframework.http.HttpStatus
import pt.isel.daw.g8.projectmanager.model.outputModel.hypermedia.ProblemJson

class WrongCredentialsException : HttpErrorException() {
    override fun toProblemJson(): ProblemJson {
        return ProblemJson(
                type = "/not-found-request",
                title = HttpStatus.UNAUTHORIZED.reasonPhrase,
                status = HttpStatus.UNAUTHORIZED.value(),
                detail = "Wrong credentials in authentication!")
    }
}