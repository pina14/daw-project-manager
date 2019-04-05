package pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations

import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation
import pt.isel.daw.g8.projectmanager.model.outputModel.mediaType.SirenModel

interface SirenRepresentation {
    val entity : EntityRepresentation?

    fun getClasses(): Array<String>?
    fun getProperties(): EntityRepresentation?
    fun getEntities(): Array<SirenModel.SirenEntity>?
    fun getActions(): Array<SirenModel.SirenAction>? //TODO implement
    fun getLinks(): Array<SirenModel.SirenLink>?
}