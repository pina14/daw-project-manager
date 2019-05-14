package pt.isel.daw.g8.projectmanager.model.outputModel

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class EmptyResponseEntity(status : HttpStatus) : ResponseEntity<String>("{}", status), OutputModel {
    override fun getMediaType(): String = "application/json"
}