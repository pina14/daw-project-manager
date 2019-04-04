package pt.isel.daw.g8.projectmanager.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.middleware.RequiresAuthentication
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.ProjectOutput
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.ProjectsOutput
import pt.isel.daw.g8.projectmanager.model.outputModel.errorRepresentations.NotFoundException
import pt.isel.daw.g8.projectmanager.model.outputModel.mediaType.SirenModel
import pt.isel.daw.g8.projectmanager.repository.ProjectRepo
import sun.reflect.generics.reflectiveObjects.NotImplementedException

@RestController
@RequestMapping(ProjectPaths.PROJECTS)
class ProjectController {

    @Autowired lateinit var projectRepo : ProjectRepo

    @PostMapping
    @RequiresAuthentication
    fun createProject() {
        //TODO Implement and set parameters
        throw NotImplementedException()
    }

    @GetMapping(produces = [SirenModel.mediaType])
    fun getUserProjects(@PathVariable(ProjectPaths.USERNAME_VAR) username: String) : OutputModel {
        val projects = projectRepo.findAll().toList()

        return ProjectsOutput(username, projects).toSiren()
    }

    @GetMapping(ProjectPaths.PROJECT_ID, produces = [SirenModel.mediaType])
    fun getProjectByName(@PathVariable(ProjectPaths.PROJECT_NAME_VAR) projectName: String) : OutputModel {
        val project = projectRepo.findById(projectName)
        if(!project.isPresent)
            throw NotFoundException("Doesn't exist a project with this name for current user.")

        return ProjectOutput(project.get()).toSiren()
    }

    @PutMapping(ProjectPaths.PROJECT_ID)
    @RequiresAuthentication
    fun updateProject() : OutputModel {
        //TODO Implement and set parameters
        throw NotImplementedException()
    }

    @DeleteMapping(ProjectPaths.PROJECT_ID)
    @RequiresAuthentication
    fun deleteProject(@PathVariable(ProjectPaths.PROJECT_NAME_VAR) projectName: String) : OutputModel {
        //TODO Implement and set parameters
        throw NotImplementedException()
    }
}
