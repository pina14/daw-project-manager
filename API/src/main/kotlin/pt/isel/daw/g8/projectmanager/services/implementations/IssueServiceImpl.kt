package pt.isel.daw.g8.projectmanager.services.implementations

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import pt.isel.daw.g8.projectmanager.model.Rules
import pt.isel.daw.g8.projectmanager.model.databaseModel.Issue
import pt.isel.daw.g8.projectmanager.model.databaseModel.ProjectAvailableStateId
import pt.isel.daw.g8.projectmanager.model.databaseModel.State
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.BadRequestException
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.ForbiddenException
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.NotFoundException
import pt.isel.daw.g8.projectmanager.model.inputModel.CreateIssueInput
import pt.isel.daw.g8.projectmanager.model.inputModel.UpdateIssueInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.IssueCollectionOutput
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.IssueOutput
import pt.isel.daw.g8.projectmanager.repository.IssueRepo
import pt.isel.daw.g8.projectmanager.repository.ProjectAvailableStateRepo
import pt.isel.daw.g8.projectmanager.repository.ProjectRepo
import pt.isel.daw.g8.projectmanager.services.interfaces.IssueService
import java.util.*

class IssueServiceImpl(private val projectRepo: ProjectRepo,
                       private val issueRepo: IssueRepo,
                       private val availableStateRepo: ProjectAvailableStateRepo) : IssueService {

    override fun createIssue(issue: CreateIssueInput): ResponseEntity<Unit> {
        val projectName = issue.projectName
        if(!projectRepo.existsById(projectName))
            throw NotFoundException("There isn't a project with name '$projectName'.")

        val project = projectRepo.findById(projectName).get()

        val issueDb = Issue(issue.issueCreator, projectName, issue.issueName, issue.description, project.defaultIssueStateName)
        issueRepo.save(issueDb)

        return ResponseEntity(HttpStatus.CREATED)
    }

    override fun getProjectIssues(projectName: String): OutputModel {
        val projectDb = projectRepo.findById(projectName)

        if(!projectDb.isPresent)
            throw NotFoundException("Project doesn't exist.")

        val project = projectDb.get()
        val issues = project.issues.sortedByDescending { it.creationDate }
        return IssueCollectionOutput(projectName, issues).toSiren()
    }

    override fun getIssueById(issueId: Int): OutputModel {
        val issue = issueRepo.findById(issueId)
        if(!issue.isPresent)
            throw NotFoundException("Doesn't exist a issue with this id.")

        return IssueOutput(issue.get()).toSiren()
    }

    override fun updateIssue(authUsername: String, issueId: Int, issue: UpdateIssueInput): ResponseEntity<Unit> {
        val oldIssueReq = issueRepo.findById(issueId)
        if(!oldIssueReq.isPresent)
            throw NotFoundException("Doesn't exist a issue with this id = $issueId.")

        val oldIssue = oldIssueReq.get()
        if(oldIssue.issueCreator != authUsername)
            throw ForbiddenException("Authentication credentials are not valid to make changes to this resource!")

        val availableStateId = ProjectAvailableStateId(oldIssue.project, State(issue.state))
        if(!availableStateRepo.existsById(availableStateId))
            throw BadRequestException("state must be available for this project.")

        var closeDate : Date? = null
        if(issue.state == Rules.CLOSED || oldIssue.closeDate == null && issue.state == Rules.ARCHIVED)
            closeDate = Date()
        else if(oldIssue.closeDate != null && issue.state == Rules.ARCHIVED)
            closeDate = oldIssue.closeDate

        val dbIssue = Issue(oldIssue.issueCreator, oldIssue.projectName, issue.issueName, issue.description, issue.state, oldIssue.creationDate, closeDate)
        dbIssue.id = oldIssue.id
        issueRepo.save(dbIssue)
        return ResponseEntity(HttpStatus.OK)
    }

    override fun deleteIssue(authUsername: String, issueId: Int): ResponseEntity<Unit> {
        val oldIssueReq = issueRepo.findById(issueId)
        if(!oldIssueReq.isPresent)
            throw NotFoundException("Doesn't exist a issue with this id = $issueId.")

        val oldIssue = oldIssueReq.get()
        if(oldIssue.issueCreator != authUsername)
            throw ForbiddenException("Authentication credentials are not valid to make changes to this resource!")

        issueRepo.deleteById(issueId)

        return ResponseEntity(HttpStatus.OK)
    }
}