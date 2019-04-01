package pt.isel.daw.g8.projectmanager.model.outputModel

import pt.isel.daw.g8.projectmanager.model.mediaModel.SirenModel

interface OutputModel {
    fun toSirenRepr() : SirenModel = SirenModel(properties = this)
}