package pt.isel.daw.g8.projectmanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import pt.isel.daw.g8.projectmanager.repository.*
import pt.isel.daw.g8.projectmanager.services.implementations.ProjectServiceImpl
import pt.isel.daw.g8.projectmanager.services.implementations.UserInfoServiceImpl
import pt.isel.daw.g8.projectmanager.services.interfaces.ProjectService
import pt.isel.daw.g8.projectmanager.services.interfaces.UserInfoService

@SpringBootApplication
class ProjectManagerApplication {
	@Bean
	fun getUserService(userRepo : UserInfoRepo) : UserInfoService = UserInfoServiceImpl(userRepo)

	@Bean
	fun getProjectService(userRepo : UserInfoRepo,
						  projectRepo: ProjectRepo,
						  labelRepo : LabelRepo,
						  stateRepo : StateRepo,
						  projectAvailableLabelRepo: ProjectAvailableLabelRepo,
						  projectAvailableStateRepo: ProjectAvailableStateRepo,
						  projectStateTransitionRepo: ProjectStateTransitionRepo) : ProjectService {
		return ProjectServiceImpl(
				userRepo,
				projectRepo,
				labelRepo,
				stateRepo,
				projectAvailableLabelRepo,
				projectAvailableStateRepo,
				projectStateTransitionRepo)
	}
}

fun main(args: Array<String>) {
	runApplication<ProjectManagerApplication>(*args)
}
