package pt.isel.daw.g8.projectmanager.model.inputModel

import pt.isel.daw.g8.projectmanager.model.databaseModel.DbModel

interface InputModel {
    fun toDbModel() : DbModel
}