package pt.isel.daw.g8.projectmanager.model.outputModel.hypermedia

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ProblemJson(
        val type : String? = null,
        val title : String? = null,
        val status : Int,
        val detail : String? = null,
        val instance : String? = null) : ErrorModel {

    @JsonIgnore
    override fun getHypermediaType(): String = "application/problem+json"
}