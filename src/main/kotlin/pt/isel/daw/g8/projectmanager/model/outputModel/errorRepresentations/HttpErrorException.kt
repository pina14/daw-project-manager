package pt.isel.daw.g8.projectmanager.model.outputModel.errorRepresentations

import pt.isel.daw.g8.projectmanager.model.outputModel.hypermedia.ProblemJson

abstract class HttpErrorException : RuntimeException() {
    abstract fun toProblemJson() : ProblemJson
}