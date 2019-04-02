package pt.isel.daw.g8.projectmanager.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.middleware.RequiresAuthentication
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EmptyOutput
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

    @GetMapping
    fun getUserProjects() : OutputModel {
        //TODO Implement and set parameters
        throw NotImplementedException()
    }

    //TODO check if complete
    @GetMapping(ProjectPaths.PROJECT_ID)
    fun getProjectByName(@PathVariable(ProjectPaths.PROJECT_NAME_VAR) projectName: String) : OutputModel {
        val project = projectRepo.findById(projectName)
        return if(project.isPresent)
            project.get().buildEntityRepresentation().toSiren()
        else
            EmptyOutput().toSiren()
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
