package pt.isel.daw.g8.projectmanager.model.databaseModel

import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel

interface DbModel {
    fun buildOutputModel() : OutputModel
}