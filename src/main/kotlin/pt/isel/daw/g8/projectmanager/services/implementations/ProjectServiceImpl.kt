package pt.isel.daw.g8.projectmanager.services.implementations

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import pt.isel.daw.g8.projectmanager.model.databaseModel.*
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.BadRequestException
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.ConflictException
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.ForbiddenException
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.NotFoundException
import pt.isel.daw.g8.projectmanager.model.inputModel.CreateProjectInput
import pt.isel.daw.g8.projectmanager.model.inputModel.StateInput
import pt.isel.daw.g8.projectmanager.model.inputModel.StateTransitionInput
import pt.isel.daw.g8.projectmanager.model.inputModel.UpdateProjectInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.ProjectCollectionOutput
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.ProjectOutput
import pt.isel.daw.g8.projectmanager.repository.*
import pt.isel.daw.g8.projectmanager.services.interfaces.ProjectService

open class ProjectServiceImpl(private val userRepo : UserInfoRepo,
                              private val projectRepo : ProjectRepo,
                              private val stateRepo : StateRepo,
                              private val projectAvailableStateRepo : ProjectAvailableStateRepo,
                              private val projectStateTransitionRepo : ProjectStateTransitionRepo) : ProjectService {

    private val mandatoryStates = listOf(StateInput("closed"), StateInput("archived"))
    private val mandatoryTransitions = listOf(StateTransitionInput("closed", "archived"))

    @Transactional
    override fun createProject(project: CreateProjectInput): ResponseEntity<Unit> {
        if(projectRepo.existsById(project.name))
            throw ConflictException("There's already a project with this name.")

        val availableStates = mutableListOf(StateInput(project.defaultStateName))
        mandatoryStates.forEach { state ->
            if(!availableStates.contains(state))
                availableStates.add(state)
        }

        mandatoryStates.forEach { state ->
            val stateDb = State(state.stateName)
            if(!stateRepo.existsById(state.stateName))
                stateRepo.save(stateDb)
        }

        val userDb = userRepo.findById(project.username).get()
        val defaultStateDb = stateRepo.findById(project.defaultStateName).get()
        val projectDb = Project(project.name, project.description, userDb, defaultStateDb)
        projectRepo.save(projectDb)

        mandatoryStates.forEach {state ->
            val projectStateId = ProjectAvailableStateId(projectDb, State(state.stateName))
            val projectStateDb = ProjectAvailableState(projectStateId)
            projectAvailableStateRepo.save(projectStateDb)
        }

        mandatoryTransitions.forEach { transition ->
            val projectStateTransitionId = ProjectStateTransitionId(projectDb, State(transition.fromState), State(transition.toState))
            val projectStateTransitionDb = ProjectStateTransition(projectStateTransitionId)
            projectStateTransitionRepo.save(projectStateTransitionDb)
        }

        return ResponseEntity(HttpStatus.CREATED)
    }

    override fun getUserProjects(username: String): OutputModel {
        val user = userRepo.findById(username)

        if(!user.isPresent)
            throw NotFoundException("User doesn't exist.")

        val projects = user.get().projects
        return ProjectCollectionOutput(username, projects).toSiren()
    }

    override fun getProjectByName(projectName: String): OutputModel {
        val project = projectRepo.findById(projectName)
        if(!project.isPresent)
            throw NotFoundException("Doesn't exist a project with this name for current user.")

        return ProjectOutput(project.get()).toSiren()
    }

    override fun updateProject(projectName : String, project: UpdateProjectInput): ResponseEntity<Unit> {
        if(!projectRepo.existsById(projectName))
            throw NotFoundException("Doesn't exist a project with this name for current user.")

        val oldProject = projectRepo.findById(projectName).get()
        val defaultState = stateRepo.findById(project.defaultStateName)
        if(!defaultState.isPresent)
            throw BadRequestException("Default state must exist.")

        val dbProject = Project(projectName, project.description, oldProject.user, defaultState.get())
        projectRepo.save(dbProject)
        return ResponseEntity(HttpStatus.OK)
    }

    override fun deleteProject(authUsername : String, projectName: String): ResponseEntity<Unit> {
        val project = projectRepo.findById(projectName)
        if(!project.isPresent)
            throw NotFoundException("Doesn't exist a project with this name for current user.")

        if(project.get().user.username != authUsername)
            throw ForbiddenException("Authentication credentials are not valid to make changes to this project!")


        projectRepo.deleteById(projectName)
        return ResponseEntity(HttpStatus.OK)
    }
}