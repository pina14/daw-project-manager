package pt.isel.daw.g8.projectmanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProjectManagerApplication

fun main(args: Array<String>) {
	runApplication<ProjectManagerApplication>(*args)
}
