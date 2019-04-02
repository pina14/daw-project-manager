package pt.isel.daw.g8.projectmanager.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.repository.IssueRepo
import pt.isel.daw.g8.projectmanager.repository.ProjectRepo
import sun.reflect.generics.reflectiveObjects.NotImplementedException

@RestController
@RequestMapping(ProjectPaths.ISSUES)
class IssueController {

    @Autowired lateinit var projectRepo : ProjectRepo
    @Autowired lateinit var issueRepo: IssueRepo

    @PostMapping
    fun createIssue() {
        //TODO Implement and set parameters
        throw NotImplementedException()
    }

    @GetMapping
    fun getProjectIssues(@PathVariable(ProjectPaths.PROJECT_NAME_VAR) projectName: String) : OutputModel {
        //TODO Implement and set parameters
        throw NotImplementedException()
    }

    @GetMapping(ProjectPaths.ISSUE_ID)
    fun getIssueById(@PathVariable(ProjectPaths.ISSUE_ID_VAR) issueId: Int) : OutputModel {
        //TODO Implement and set parameters
        throw NotImplementedException()
    }

    @PutMapping(ProjectPaths.ISSUE_ID)
    fun updateIssue() {
        //TODO Implement and set parameters
        throw NotImplementedException()
    }

    @DeleteMapping(ProjectPaths.ISSUE_ID)
    fun deleteIssue(@PathVariable(ProjectPaths.ISSUE_ID_VAR) issueId: Int) : OutputModel {
        //TODO Implement and set parameters
        throw NotImplementedException()
    }
}
