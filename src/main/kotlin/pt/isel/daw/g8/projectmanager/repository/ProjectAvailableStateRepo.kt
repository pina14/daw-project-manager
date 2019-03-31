package pt.isel.daw.g8.projectmanager.repository

import org.springframework.data.repository.CrudRepository
import pt.isel.daw.g8.projectmanager.model.databaseModel.ProjectAvailableState
import pt.isel.daw.g8.projectmanager.model.databaseModel.ProjectAvailableStateId

interface ProjectAvailableStateRepo : CrudRepository<ProjectAvailableState, ProjectAvailableStateId>