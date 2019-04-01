package pt.isel.daw.g8.projectmanager.model.outputModel

import pt.isel.daw.g8.projectmanager.model.mediaModel.SirenModel

class EmptyOutput : OutputModel {
    override fun toSirenRepr(): SirenModel = SirenModel()
}