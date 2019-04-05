package pt.isel.daw.g8.projectmanager.model.errorModel

class ProblemJson(
        val type : String? = null,
        val title : String? = null,
        val status : Int,
        val detail : String? = null,
        val instance : String? = null) : ErrorModel {

    companion object {
        const val mediaType = "application/problem+json"
    }

    override fun getMediaType(): String = mediaType
}