package pt.isel.daw.g8.projectmanager.middleware

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import pt.isel.daw.g8.projectmanager.loggerFor
import pt.isel.daw.g8.projectmanager.model.errorModel.ErrorModel
import pt.isel.daw.g8.projectmanager.model.errorModel.ProblemJson
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.HttpErrorException

private val log = loggerFor<ApiHandlerExceptionResolver>()

@RestControllerAdvice
class ApiHandlerExceptionResolver : ResponseEntityExceptionHandler() {

    @ExceptionHandler(Throwable::class)
    fun handleAll(ex: Exception): ResponseEntity<ErrorModel> {
        log.error(ex.message, ex)
        val error = ProblemJson(
                title = HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
                status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                detail = "Sorry, internal error...")
        return ResponseEntity.status(error.status)
                .header("Content-Type", error.getMediaType())
                .body(error)
    }

    @ExceptionHandler(HttpErrorException::class)
    fun handleHttpErrorGeneric(ex : HttpErrorException) : ResponseEntity<ErrorModel> {
        val error =  ex.toProblemJson()
        log.warn(error.detail, ex)
        return ResponseEntity
                .status(error.status)
                .header("Content-Type", error.getMediaType())
                .body(error)
    }
}