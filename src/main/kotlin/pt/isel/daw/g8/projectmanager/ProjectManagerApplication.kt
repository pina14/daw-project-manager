package pt.isel.daw.g8.projectmanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import pt.isel.daw.g8.projectmanager.repository.*
import pt.isel.daw.g8.projectmanager.services.implementations.IssueServiceImpl
import pt.isel.daw.g8.projectmanager.services.implementations.ProjectServiceImpl
import pt.isel.daw.g8.projectmanager.services.implementations.UserInfoServiceImpl
import pt.isel.daw.g8.projectmanager.services.interfaces.IssueService
import pt.isel.daw.g8.projectmanager.services.interfaces.ProjectService
import pt.isel.daw.g8.projectmanager.services.interfaces.UserInfoService

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
}

fun main(args: Array<String>) {
	runApplication<ProjectManagerApplication>(*args)
}
