package pt.isel.daw.g8.projectmanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import pt.isel.daw.g8.projectmanager.repository.UserInfoRepo
import pt.isel.daw.g8.projectmanager.services.implementations.UserInfoServiceImpl
import pt.isel.daw.g8.projectmanager.services.interfaces.UserInfoService

@SpringBootApplication
class ProjectManagerApplication {
	@Bean
	fun getUserService(userRepo : UserInfoRepo) : UserInfoService = UserInfoServiceImpl(userRepo)
}

fun main(args: Array<String>) {
	runApplication<ProjectManagerApplication>(*args)
}
