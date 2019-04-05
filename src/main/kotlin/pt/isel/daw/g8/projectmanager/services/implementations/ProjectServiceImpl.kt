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
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.ProjectOutput
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.ProjectsOutput
import pt.isel.daw.g8.projectmanager.repository.*
import pt.isel.daw.g8.projectmanager.services.interfaces.ProjectService

open class ProjectServiceImpl(private val userRepo : UserInfoRepo,
                              private val projectRepo : ProjectRepo,
                              private val labelRepo : LabelRepo,
                              private val stateRepo : StateRepo,
                              private val projectAvailableLabelRepo : ProjectAvailableLabelRepo,
                              private val projectAvailableStateRepo : ProjectAvailableStateRepo,
                              private val projectStateTransitionRepo : ProjectStateTransitionRepo) : ProjectService {

    private val mandatoryStates = listOf(StateInput("closed"), StateInput("archived"))
    private val mandatoryTransitions = listOf(StateTransitionInput("closed", "archived"))

    @Transactional
    override fun createProject(project: CreateProjectInput): ResponseEntity<Unit> {
        if(projectRepo.existsById(project.name))
            throw ConflictException("There's already a project with this name.")

        mandatoryStates.forEach { state ->
            project.availableStates.let { availableStates ->
                if(!availableStates.contains(state))
                    availableStates.add(state)
            }
        }

        mandatoryTransitions.forEach { transition ->
            project.availableStateTransitions.let { availableTransition ->
                if(!availableTransition.contains(transition))
                    availableTransition.add(transition)
            }
        }

        if(!project.availableStates.any { state -> project.defaultStateName == state.stateName})
            throw BadRequestException("Default state must exist in availableStates set.")

        project.availableLabels?.forEach { label ->
            val labelDb = Label(label.labelName)
            if(!labelRepo.existsById(label.labelName))
                labelRepo.save(labelDb)
        }

        project.availableStates.forEach { state ->
            val stateDb = State(state.stateName)
            if(!stateRepo.existsById(state.stateName))
                stateRepo.save(stateDb)
        }

        val userDb = userRepo.findById(project.username).get()
        val defaultStateDb = stateRepo.findById(project.defaultStateName).get()
        val projectDb = Project(project.name, project.description, userDb, defaultStateDb)
        projectRepo.save(projectDb)

        project.availableLabels?.forEach {
            val projectLabelId = ProjectAvailableLabelId(projectDb, Label(it.labelName))
            val projectLabelDb = ProjectAvailableLabel(projectLabelId)
            projectAvailableLabelRepo.save(projectLabelDb)
        }

        project.availableStates.forEach {
            val projectStateId = ProjectAvailableStateId(projectDb, State(it.stateName))
            val projectStateDb = ProjectAvailableState(projectStateId)
            projectAvailableStateRepo.save(projectStateDb)
        }

        project.availableStateTransitions.forEach {
            val projectStateTransitionId = ProjectStateTransitionId(projectDb, State(it.fromState), State(it.toState))
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
        return ProjectsOutput(username, projects).toSiren()
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