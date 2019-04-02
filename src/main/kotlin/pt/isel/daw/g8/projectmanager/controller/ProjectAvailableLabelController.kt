package pt.isel.daw.g8.projectmanager.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.middleware.RequiresAuthentication
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.repository.ProjectRepo
import sun.reflect.generics.reflectiveObjects.NotImplementedException

@RestController
@RequestMapping(ProjectPaths.LABELS)
class ProjectAvailableLabelController {

    @Autowired lateinit var projectRepo : ProjectRepo

    @PostMapping
    @RequiresAuthentication
    fun addProjectAvailableLabel() {
        //TODO Implement and set parameters
        throw NotImplementedException()
    }

    @GetMapping
    fun getProjectAvailableLabels(@PathVariable(ProjectPaths.PROJECT_NAME_VAR) projectName: String) : OutputModel {
        //TODO Implement and set parameters
        throw NotImplementedException()
    }

    @DeleteMapping(ProjectPaths.LABEL_ID)
    @RequiresAuthentication
    fun deleteProjectAvailableLabel(
            @PathVariable(ProjectPaths.PROJECT_NAME_VAR) projectName: String,
            @PathVariable(ProjectPaths.LABEL_NAME_VAR) labelName: String) : OutputModel {
        //TODO Implement and set parameters
        throw NotImplementedException()
    }
}
