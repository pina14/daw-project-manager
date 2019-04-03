package pt.isel.daw.g8.projectmanager.model.outputModel.hypermedia

import com.fasterxml.jackson.annotation.JsonProperty
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation

class SirenModel(
        @JsonProperty("class") val _class : Array<String>? = null,
        @JsonProperty("properties") val properties : EntityRepresentation? = null,
        @JsonProperty("entities") val entities : Array<SirenEntity>? = null,
        @JsonProperty("actions") val actions : Array<SirenAction>? = null,
        @JsonProperty("links") val links : Array<SirenLink>? = null) : OutputModel {

    override fun getHypermediaType(): String = "application/vnd.siren+json"

    class SirenLink(val rel : Array<String>, val href : String)

    class SirenAction

    class SirenEntity
}