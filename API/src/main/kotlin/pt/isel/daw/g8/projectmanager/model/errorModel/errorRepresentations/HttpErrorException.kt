package pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations

import pt.isel.daw.g8.projectmanager.model.errorModel.ProblemJson

abstract class HttpErrorException : RuntimeException() {
    abstract var errorMessage : String
    abstract fun toProblemJson() : ProblemJson
}