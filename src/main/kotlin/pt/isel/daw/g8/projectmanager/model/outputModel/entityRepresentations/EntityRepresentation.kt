package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import com.fasterxml.jackson.annotation.JsonIgnore
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.mediaType.SirenModel

interface EntityRepresentation : OutputModel {
    fun toSiren() : SirenModel = SirenModel(properties = this)

    @JsonIgnore
    override fun getMediaType(): String = "application/json"
}