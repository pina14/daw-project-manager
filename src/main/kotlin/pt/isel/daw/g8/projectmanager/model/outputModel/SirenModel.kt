package pt.isel.daw.g8.projectmanager.model.outputModel

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation
import pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations.SirenRepresentation

class SirenModel(
        @JsonProperty("class") val _class : Array<String>? = null,
        @JsonProperty("properties") val properties : EntityRepresentation? = null,
        @JsonProperty("entities") val entities : Array<SirenEntity>? = null,
        @JsonProperty("actions") val actions : Array<SirenAction>? = null,
        @JsonProperty("links") val links : Array<SirenLink>? = null) : OutputModel {

    constructor(sirenRepresentation: SirenRepresentation) : this(
            sirenRepresentation.getClasses(),
            sirenRepresentation.getProperties(),
            sirenRepresentation.getEntities(),
            sirenRepresentation.getActions(),
            sirenRepresentation.getLinks()
    )

    companion object {
        const val mediaType = "application/vnd.siren+json"
    }

    override fun getMediaType(): String = mediaType

    class SirenLink(val rel : Array<String>, val href : String)

    @JsonInclude(JsonInclude.Include.NON_NULL)
    class SirenAction(@JsonProperty("name") val name : String,
                      @JsonProperty("title") val title : String? = null,
                      @JsonProperty("method") val method : String? = null,
                      @JsonProperty("href") val href : String,
                      @JsonProperty("type") val type : String? = null,
                      @JsonProperty("fields") val fields : Array<SirenActionField>? = null)

    @JsonInclude(JsonInclude.Include.NON_NULL)
    class SirenActionField(@JsonProperty("class") val _class : Array<String>?,
                           @JsonProperty("name") val name : String,
                          @JsonProperty("type") val type : String,
                          @JsonProperty("value") val value : String? = null)

    @JsonInclude(JsonInclude.Include.NON_NULL)
    interface SirenEntity

    class SirenEmbeddedLink(@JsonProperty("class") val _class : Array<String>? = null,
                            @JsonProperty("rel") val rel : Array<String>,
                            @JsonProperty("href") val href : String) : SirenEntity

    class SirenEmbeddedRepresentation(@JsonProperty("class") val _class : Array<String>? = null,
                                      @JsonProperty("rel") val rel : Array<String>? = null,
                                      @JsonProperty("properties") val properties : EntityRepresentation? = null,
                                      @JsonProperty("entities") val entities : Array<SirenEntity>? = null,
                                      @JsonProperty("actions") val actions : Array<SirenAction>? = null,
                                      @JsonProperty("links") val links : Array<SirenLink>? = null) : SirenEntity
}