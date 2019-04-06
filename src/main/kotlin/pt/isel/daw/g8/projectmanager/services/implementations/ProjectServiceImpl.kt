package pt.isel.daw.g8.projectmanager.services.implementations

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import pt.isel.daw.g8.projectmanager.model.Rules
import pt.isel.daw.g8.projectmanager.model.databaseModel.*
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.BadRequestException
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.ConflictException
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.ForbiddenException
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.NotFoundException
import pt.isel.daw.g8.projectmanager.model.inputModel.CreateProjectInput
import pt.isel.daw.g8.projectmanager.model.inputModel.StateInput
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

    @Transactional
    override fun createProject(project: CreateProjectInput): ResponseEntity<Unit> {
        if(projectRepo.existsById(project.name))
            throw ConflictException("There's already a project with this name.")

        val availableStates = mutableListOf(StateInput(project.defaultStateName))
        Rules.mandatoryStates.forEach { state ->
            if(!availableStates.contains(state))
                availableStates.add(state)
        }

        availableStates.forEach { state ->
            val stateDb = State(state.stateName)
            if(!stateRepo.existsById(state.stateName))
                stateRepo.save(stateDb)
        }

        val projectDb = Project(project.name, project.description, project.username, project.defaultStateName)
        projectRepo.save(projectDb)

        availableStates.forEach {state ->
            val projectStateId = ProjectAvailableStateId(projectDb, State(state.stateName))
            val projectStateDb = ProjectAvailableState(projectStateId)
            projectAvailableStateRepo.save(projectStateDb)
        }

        Rules.mandatoryTransitions.forEach { transition ->
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

    override fun updateProject(authUsername: String, projectName: String, project: UpdateProjectInput): ResponseEntity<Unit> {
        val oldProjectReq = projectRepo.findById(projectName)
        if(!oldProjectReq.isPresent)
            throw NotFoundException("Doesn't exist a project with this name for current user.")

        val oldProject = oldProjectReq.get()
        if(oldProject.username != authUsername)
            throw ForbiddenException("Authentication credentials are not valid to make changes to this resource!")

        if(!stateRepo.existsById(project.defaultStateName))
            throw BadRequestException("Default state must exist.")

        val dbProject = Project(projectName, project.description, authUsername, project.defaultStateName)
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