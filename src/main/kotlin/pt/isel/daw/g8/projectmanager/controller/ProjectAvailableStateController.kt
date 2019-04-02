package pt.isel.daw.g8.projectmanager.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.repository.ProjectRepo
import sun.reflect.generics.reflectiveObjects.NotImplementedException

@RestController
@RequestMapping(ProjectPaths.STATES)
class ProjectAvailableStateController {

    @Autowired lateinit var projectRepo : ProjectRepo

    @PostMapping
    fun addProjectAvailableState() {
        //TODO Implement and set parameters
        throw NotImplementedException()
    }

    @GetMapping
    fun getProjectAvailableStates(@PathVariable(ProjectPaths.PROJECT_NAME_VAR) projectName: String) : OutputModel {
        //TODO Implement and set parameters
        throw NotImplementedException()
    }

    @DeleteMapping(ProjectPaths.STATE_ID)
    fun deleteProjectAvailableState(
            @PathVariable(ProjectPaths.PROJECT_NAME_VAR) projectName: String,
            @PathVariable(ProjectPaths.STATE_NAME_VAR) stateName: String) : OutputModel {
        //TODO Implement and set parameters
        throw NotImplementedException()
    }
}
