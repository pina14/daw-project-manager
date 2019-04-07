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
	fun getUserService(userRepo : UserInfoRepo) : UserInfoService = UserInfoServiceImpl(userRepo)

	@Bean
	fun getProjectService(userRepo : UserInfoRepo,
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
	fun getCommentService(issueRepo : IssueRepo, commentRepo: CommentRepo) : CommentService = CommentServiceImpl(issueRepo, commentRepo)

	@Bean
	fun getIssueLabelService(issueRepo : IssueRepo, projectAvailableLabelRepo: ProjectAvailableLabelRepo, issueLabelRepo: IssueLabelRepo)
			: IssueLabelService = IssueLabelServiceImpl(issueRepo, projectAvailableLabelRepo, issueLabelRepo)

	@Bean
	fun getProjectAvailableLabelService(projectRepo : ProjectRepo, labelRepo : LabelRepo, projectAvailableLabelRepo: ProjectAvailableLabelRepo)
			: ProjectAvailabeLabelService = ProjectAvailableLabelServiceImpl(projectRepo, labelRepo, projectAvailableLabelRepo)
}

fun main(args: Array<String>) {
	runApplication<ProjectManagerApplication>(*args)
}
