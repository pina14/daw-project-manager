package pt.isel.daw.g8.projectmanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import pt.isel.daw.g8.projectmanager.repository.*
import pt.isel.daw.g8.projectmanager.services.implementations.*
import pt.isel.daw.g8.projectmanager.services.interfaces.*

@SpringBootApplication
class ProjectManagerApplication {
	@Bean
	fun getUserService(userRepo : UserRepo) : UserService = UserServiceImpl(userRepo)

	@Bean
	fun getProjectService(userRepo : UserRepo,
						  projectRepo: ProjectRepo,
						  stateRepo : StateRepo,
						  projectAvailableStateRepo: ProjectAvailableStateRepo,
						  projectStateTransitionRepo: ProjectStateTransitionRepo) : ProjectService {
		return ProjectServiceImpl(
				userRepo,
				projectRepo,
				stateRepo,
				projectAvailableStateRepo,
				projectStateTransitionRepo)
	}

	@Bean
	fun getIssueService(projectRepo: ProjectRepo, issueRepo : IssueRepo, projectAvailableStateRepo: ProjectAvailableStateRepo) : IssueService
			= IssueServiceImpl(projectRepo, issueRepo, projectAvailableStateRepo)

	@Bean
	fun getCommentService(issueRepo : IssueRepo, commentRepo: IssueCommentRepo) : IssueCommentService = IssueCommentServiceImpl(issueRepo, commentRepo)

	@Bean
	fun getIssueLabelService(issueRepo : IssueRepo, projectAvailableLabelRepo: ProjectAvailableLabelRepo, issueLabelRepo: IssueLabelRepo)
			: IssueLabelService = IssueLabelServiceImpl(issueRepo, projectAvailableLabelRepo, issueLabelRepo)

	@Bean
	fun getProjectAvailableLabelService(projectRepo : ProjectRepo, labelRepo : LabelRepo, projectAvailableLabelRepo: ProjectAvailableLabelRepo)
			: ProjectAvailabeLabelService = ProjectAvailableLabelServiceImpl(projectRepo, labelRepo, projectAvailableLabelRepo)

	@Bean
	fun getProjectAvailableStateService(projectRepo : ProjectRepo, stateRepo: StateRepo, projectAvailableStateRepo: ProjectAvailableStateRepo)
			: ProjectAvailableStateService = ProjectAvailableStateServiceImpl(projectRepo, stateRepo, projectAvailableStateRepo)

	@Bean
	fun getProjectStateTransitionService(projectRepo : ProjectRepo, projectAvailableStateRepo: ProjectAvailableStateRepo, projectStateTransitionRepo : ProjectStateTransitionRepo)
			: ProjectStateTransitionService = ProjectStateTransitionServiceImpl(projectRepo, projectAvailableStateRepo, projectStateTransitionRepo)
}

fun main(args: Array<String>) {
	runApplication<ProjectManagerApplication>(*args)
}
