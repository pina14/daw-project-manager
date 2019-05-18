package pt.isel.daw.g8.projectmanager.services.implementations

import org.springframework.http.HttpStatus
import pt.isel.daw.g8.projectmanager.model.Rules
import pt.isel.daw.g8.projectmanager.model.databaseModel.ProjectAvailableStateId
import pt.isel.daw.g8.projectmanager.model.databaseModel.ProjectStateTransition
import pt.isel.daw.g8.projectmanager.model.databaseModel.ProjectStateTransitionId
import pt.isel.daw.g8.projectmanager.model.databaseModel.State
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.BadRequestException
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.ConflictException
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.ForbiddenException
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.NotFoundException
import pt.isel.daw.g8.projectmanager.model.inputModel.ProjectStateTransitionInput
import pt.isel.daw.g8.projectmanager.model.outputModel.EmptyResponseEntity
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.ProjectStateTransitionCollectionOutput
import pt.isel.daw.g8.projectmanager.repository.ProjectAvailableStateRepo
import pt.isel.daw.g8.projectmanager.repository.ProjectRepo
import pt.isel.daw.g8.projectmanager.repository.ProjectStateTransitionRepo
import pt.isel.daw.g8.projectmanager.services.interfaces.ProjectStateTransitionService

class ProjectStateTransitionServiceImpl(private val projectRepo: ProjectRepo,
                                        private val projectAvailableStateRepo : ProjectAvailableStateRepo,
                                        private val projectStateTransitionRepo : ProjectStateTransitionRepo) : ProjectStateTransitionService {

    override fun addProjectStateTransition(authUsername: String, projectStateTransition: ProjectStateTransitionInput): EmptyResponseEntity {
        val projectName = projectStateTransition.projectName
        if(!projectRepo.existsById(projectName))
            throw NotFoundException("Doesn't exist a project with name '$projectName'.")

        val projectDb = projectRepo.findById(projectName).get()
        if(authUsername != projectDb.username)
            throw ForbiddenException("Authentication credentials are not valid to make changes to this resource.")

        val fromState = State(projectStateTransition.fromState)
        val fromAvailableStateId = ProjectAvailableStateId(projectDb, fromState)
        if(!projectAvailableStateRepo.existsById(fromAvailableStateId))
            throw BadRequestException("State '${fromState.stateName}' isn't allowed for this project.")

        val toState = State(projectStateTransition.toState)
        val toAvailableStateId = ProjectAvailableStateId(projectDb, toState)
        if(!projectAvailableStateRepo.existsById(toAvailableStateId))
            throw BadRequestException("State '${toState.stateName}' isn't allowed for this project.")

        if(projectStateTransition.fromState == projectStateTransition.toState)
            throw BadRequestException("Transition has to be between 2 different states.")

        val projectStateTransitionId = ProjectStateTransitionId(projectDb, fromState, toState)
        if(projectStateTransitionRepo.existsById(projectStateTransitionId))
            throw ConflictException("This state transition already exists for this project.")

        val projectStateTransitionDb = ProjectStateTransition(projectStateTransitionId)

        projectStateTransitionRepo.save(projectStateTransitionDb)

        return EmptyResponseEntity(HttpStatus.CREATED)
    }

    override fun getProjectStateTransitions(projectName: String): OutputModel {
        if(!projectRepo.existsById(projectName))
            throw NotFoundException("There isn't a project with name = '$projectName'.")

        val projectDb = projectRepo.findById(projectName).get()
        return ProjectStateTransitionCollectionOutput(projectName, projectDb.availableStateTransitions).toSiren()
    }

    override fun deleteProjectStateTransition(authUsername: String, projectName: String, fromState: String, toState: String): EmptyResponseEntity {
        if(Rules.isMandatoryTransition(fromState, toState))
            throw BadRequestException("It's not allowed to delete this transition.")

        if(!projectRepo.existsById(projectName))
            throw NotFoundException("There isn't a project with name '$projectName'.")
        val projectDb = projectRepo.findById(projectName).get()

        if(projectDb.username != authUsername)
            throw ForbiddenException("Authentication credentials are not valid to make changes to this resource.")

        val projectStateTransitionId = ProjectStateTransitionId(projectDb, State(fromState), State(toState))
        if(!projectStateTransitionRepo.existsById(projectStateTransitionId))
            throw NotFoundException("Project state transition doesn't exist.")

        projectStateTransitionRepo.deleteById(projectStateTransitionId)

        return EmptyResponseEntity(HttpStatus.OK)
    }
}