package pt.isel.daw.g8.projectmanager.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.repository.IssueRepo
import pt.isel.daw.g8.projectmanager.repository.ProjectRepo
import sun.reflect.generics.reflectiveObjects.NotImplementedException

@RestController
@RequestMapping(ProjectPaths.ISSUE_LABELS)
class IssueLabelController {

    @Autowired lateinit var projectRepo : ProjectRepo
    @Autowired lateinit var issueRepo: IssueRepo

    @PostMapping
    fun addIssueLabel() {
        //TODO Implement and set parameters
        throw NotImplementedException()
    }

    @GetMapping
    fun getIssueLabels(@PathVariable(ProjectPaths.ISSUE_ID_VAR) issueId: Int) : OutputModel {
        //TODO Implement and set parameters
        throw NotImplementedException()
    }

    @DeleteMapping(ProjectPaths.ISSUE_LABEL_ID)
    fun deleteIssueLabel(
            @PathVariable(ProjectPaths.PROJECT_NAME_VAR) projectName: String,
            @PathVariable(ProjectPaths.ISSUE_ID_VAR) issueId: Int,
            @PathVariable(ProjectPaths.LABEL_NAME_VAR) labelName: String) : OutputModel {
        //TODO Implement and set parameters
        throw NotImplementedException()
    }
}
