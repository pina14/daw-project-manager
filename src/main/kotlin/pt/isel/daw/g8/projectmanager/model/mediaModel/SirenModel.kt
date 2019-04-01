package pt.isel.daw.g8.projectmanager.model.mediaModel

import com.fasterxml.jackson.annotation.JsonProperty
import pt.isel.daw.g8.projectmanager.model.outputModel.EmptyOutput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel

class SirenModel(
        @JsonProperty("class") val _class : Array<String> = arrayOf(),
        @JsonProperty("properties") val properties : OutputModel = EmptyOutput(),
        @JsonProperty("entities") val entities : Array<SirenEntity> = arrayOf(),
        @JsonProperty("actions") val actions : Array<SirenAction> = arrayOf(),
        @JsonProperty("links") val links : Array<SirenLink> = arrayOf()) : MediaModel

class SirenLink {

}

class SirenAction {

}

class SirenEntity {

}
