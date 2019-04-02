package pt.isel.daw.g8.projectmanager.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pt.isel.daw.g8.projectmanager.loggerFor
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EmptyOutput
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

    @GetMapping("/{name}")
    fun getProjectByName(@PathVariable("name") name: String) : OutputModel {
        val project = projectRepo.findById(name)
        return if(project.isPresent)
            project.get().buildEntityRepresentation().toSiren()
        else
            EmptyOutput().toSiren()
    }

    @GetMapping("/{name}/issues")
    fun getProjectIssues(@PathVariable("name") name: String) : OutputModel {
        val project = projectRepo.findById(name)
        return if(project.isPresent)
            project.get().issues.firstOrNull()?.buildEntityRepresentation()?.toSiren() ?: EmptyOutput()
        else
            EmptyOutput()
    }
}
