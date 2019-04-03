package pt.isel.daw.g8.projectmanager.model.outputModel.mediaType

import pt.isel.daw.g8.projectmanager.model.outputModel.ErrorModel

class ProblemJson(
        val type : String? = null,
        val title : String? = null,
        val status : Int,
        val detail : String? = null,
        val instance : String? = null) : ErrorModel {

    override fun getHypermediaType(): String = "application/problem+json"
}