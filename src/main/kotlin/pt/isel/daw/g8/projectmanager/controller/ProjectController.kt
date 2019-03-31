package pt.isel.daw.g8.projectmanager.controller

import org.springframework.beans.factory.annotation.Autowired
import pt.isel.daw.g8.projectmanager.loggerFor
import org.springframework.web.bind.annotation.*
import pt.isel.daw.g8.projectmanager.model.databaseModel.*
import pt.isel.daw.g8.projectmanager.repository.*

private val log = loggerFor<ProjectController>()

@RestController
@RequestMapping("/projects")
class ProjectController {

    @Autowired lateinit var projectRepo : ProjectRepo
    @Autowired lateinit var userRepo : UserInfoRepo
    @Autowired lateinit var issueRepo : IssueRepo
    @Autowired lateinit var commentRepo : CommentRepo
    @Autowired lateinit var issueStateRepo : IssueStateRepo
    @Autowired lateinit var availableStatesRepo : ProjectAvailableStateRepo

    @GetMapping
    fun getAllUserProjects(@RequestParam("username") username: String) : List<Project> {
        val userInfo = userRepo.findById(username)

        return if(userInfo.isPresent)
            userInfo.get().projects
        else
            listOf()
    }

    @GetMapping("/{name}")
    fun getProjectByName(@PathVariable("name") name: String) = projectRepo.findById(name)

    @GetMapping("/state")
    fun getAllProjectsInStateX(@RequestParam("state") stateName: String) : List<Project> {
        val state = issueStateRepo.findById(stateName)

        return if(state.isPresent)
            state.get().projects
        else
            listOf()
    }

    @GetMapping("{projectName}/availableStates")
    fun getProjectAvailableStates(@PathVariable("projectName") projectName: String) : List<ProjectAvailableState> {
        val project = projectRepo.findById(projectName)

        return if(project.isPresent)
            project.get().availableStates
        else
            listOf()
    }

    @GetMapping("{projectName}/availableStateTransitions")
    fun getProjectAvailableStateTransitions(@PathVariable("projectName") projectName: String) : List<ProjectStateTransition> {
        val project = projectRepo.findById(projectName)

        return if(project.isPresent)
            project.get().availableStateTransitions
        else
            listOf()
    }

    @GetMapping("{projectName}/availableLabels")
    fun getProjectAvailableLabels(@PathVariable("projectName") projectName: String) : List<ProjectAvailableLabel> {
        val project = projectRepo.findById(projectName)

        return if(project.isPresent)
            project.get().availableLabels
        else
            listOf()
    }

    @GetMapping("{projectName}/issues")
    fun getProjectIssues(@PathVariable("projectName") projectName: String) : List<Issue> {
        val project = projectRepo.findById(projectName)

        return if(project.isPresent)
            project.get().issues
        else
            listOf()
    }

    @GetMapping("{projectName}/issues/{issueId}/labels")
    fun getProjectIssueLabels(@PathVariable("projectName") projectName: String,
                         @PathVariable("issueId") issueId: Int) : List<ProjectIssueLabel> {
        val issue = issueRepo.findById(issueId)

        return if(issue.isPresent)
            issue.get().labels
        else
            listOf()
    }

    @GetMapping("{projectName}/issues/{issueId}/comments")
    fun getProjectIssueComments(@PathVariable("projectName") projectName: String,
                              @PathVariable("issueId") issueId: Int) : List<Comment> {
        val issueComments = commentRepo.findAllByIssueId(issueId)
        val issue = issueRepo.findById(issueId)

        return if(issueComments.isPresent)
            issueComments.get()
        else
            listOf()
    }
}
