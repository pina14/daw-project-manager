package pt.isel.daw.g8.projectmanager.model.outputModel

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
interface OutputModel {
    @JsonIgnore
    fun getHypermediaType() : String
}
