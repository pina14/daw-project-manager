package pt.isel.daw.g8.projectmanager.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import pt.isel.daw.g8.projectmanager.model.outputModel.hypermedia.ProblemJson

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException : HttpErrorException() {
    override fun toProblemJson(): ProblemJson {
        return ProblemJson(
                type = "/not-found-request",
                title = HttpStatus.NOT_FOUND.reasonPhrase,
                status = HttpStatus.NOT_FOUND.value(),
                detail = "This resource doesn't exists!")
    }
}