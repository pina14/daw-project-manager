package pt.isel.daw.g8.projectmanager.model.databaseModel

import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation

interface DbModel {
    fun buildEntityRepresentation() : EntityRepresentation
}