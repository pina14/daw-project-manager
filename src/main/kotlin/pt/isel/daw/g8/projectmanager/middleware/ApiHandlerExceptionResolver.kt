package pt.isel.daw.g8.projectmanager.middleware

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import pt.isel.daw.g8.projectmanager.model.outputModel.errorRepresentations.HttpErrorException
import pt.isel.daw.g8.projectmanager.model.outputModel.hypermedia.ErrorModel
import pt.isel.daw.g8.projectmanager.model.outputModel.hypermedia.ProblemJson

@RestControllerAdvice
class ApiHandlerExceptionResolver : ResponseEntityExceptionHandler() {

    @ExceptionHandler(Throwable::class)
    fun handleAll(ex: Exception): ResponseEntity<ErrorModel> {
        val error = ProblemJson(
                title = HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
                status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                detail = "Sorry, internal error...")
        return ResponseEntity.status(error.status)
                .header("Content-Type", error.getHypermediaType())
                .body(error)
    }

    @ExceptionHandler(HttpErrorException::class)
    fun handleHttpErrorGeneric(ex : HttpErrorException) : ResponseEntity<ErrorModel> {
        val error =  ex.toProblemJson()
        return ResponseEntity
                .status(error.status)
                .header("Content-Type", error.getHypermediaType())
                .body(error)
    }
}