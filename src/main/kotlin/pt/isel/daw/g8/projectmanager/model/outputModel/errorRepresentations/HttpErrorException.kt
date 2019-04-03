package pt.isel.daw.g8.projectmanager.model.outputModel.errorRepresentations

import pt.isel.daw.g8.projectmanager.model.outputModel.mediaType.ProblemJson

abstract class HttpErrorException : RuntimeException() {
    abstract var errorMessage : String
    abstract fun toProblemJson() : ProblemJson
}