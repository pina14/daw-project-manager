package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.hypermedia.SirenModel

interface EntityRepresentation : OutputModel {
    fun toSiren() : SirenModel = SirenModel(properties = this)
}