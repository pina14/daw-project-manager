package pt.isel.daw.g8.projectmanager.services.implementations

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import pt.isel.daw.g8.projectmanager.model.Rules
import pt.isel.daw.g8.projectmanager.model.databaseModel.ProjectAvailableState
import pt.isel.daw.g8.projectmanager.model.databaseModel.ProjectAvailableStateId
import pt.isel.daw.g8.projectmanager.model.databaseModel.State
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.BadRequestException
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.ConflictException
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.ForbiddenException
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.NotFoundException
import pt.isel.daw.g8.projectmanager.model.inputModel.ProjectAvailableStateInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.ProjectAvailableStateCollectionOutput
import pt.isel.daw.g8.projectmanager.repository.ProjectAvailableStateRepo
import pt.isel.daw.g8.projectmanager.repository.ProjectRepo
import pt.isel.daw.g8.projectmanager.repository.StateRepo
import pt.isel.daw.g8.projectmanager.services.interfaces.ProjectAvailableStateService

class ProjectAvailableStateServiceImpl(private val projectRepo: ProjectRepo,
                                       private val stateRepo : StateRepo,
                                       private val projectAvailableStateRepo : ProjectAvailableStateRepo) : ProjectAvailableStateService {

    override fun addProjectAvailableState(authUsername: String, projectAvailableState: ProjectAvailableStateInput): ResponseEntity<Unit> {
        val projectName = projectAvailableState.projectName
        if(!projectRepo.existsById(projectName))
            throw NotFoundException("Doesn't exist a project with name '$projectName'.")

        val projectDb = projectRepo.findById(projectName).get()
        if(authUsername != projectDb.username)
            throw ForbiddenException("Authentication credentials are not valid to make changes to this resource.")

        val state = State(projectAvailableState.stateName)
        if(!stateRepo.existsById(state.stateName))
            stateRepo.save(state)

        val projectAvailableStateId = ProjectAvailableStateId(projectDb, state)
        if(projectAvailableStateRepo.existsById(projectAvailableStateId))
            throw ConflictException("This state already exists for this project.")

        val projectAvailableStateDb = ProjectAvailableState(projectAvailableStateId)

        projectAvailableStateRepo.save(projectAvailableStateDb)

        return ResponseEntity(HttpStatus.CREATED)
    }

    override fun getProjectAvailableStates(projectName: String): OutputModel {
        if(!projectRepo.existsById(projectName))
            throw NotFoundException("There isn't a project with name = '$projectName'.")

        val projectDb = projectRepo.findById(projectName).get()
        return ProjectAvailableStateCollectionOutput(projectName, projectDb.availableStates).toSiren()
    }

    override fun deleteProjectAvailableState(authUsername: String, projectName: String, stateName: String): ResponseEntity<Unit> {
        if(Rules.mandatoryStates.contains(stateName))
            throw BadRequestException("It's not allowed to delete this state.")

        if(!projectRepo.existsById(projectName))
            throw NotFoundException("There isn't a project with name '$projectName'.")
        val projectDb = projectRepo.findById(projectName).get()

        if(projectDb.username != authUsername)
            throw ForbiddenException("Authentication credentials are not valid to make changes to this resource.")

        val stateDb = State(stateName)

        val projectAvailableStateId = ProjectAvailableStateId(projectDb, stateDb)
        if(!projectAvailableStateRepo.existsById(projectAvailableStateId))
            throw NotFoundException("Project State doesn't exist.")

        projectAvailableStateRepo.deleteById(projectAvailableStateId)

        return ResponseEntity(HttpStatus.OK)
    }
}