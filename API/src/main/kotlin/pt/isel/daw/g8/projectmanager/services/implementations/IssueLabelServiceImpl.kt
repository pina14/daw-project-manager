package pt.isel.daw.g8.projectmanager.services.implementations

import org.springframework.http.HttpStatus
import pt.isel.daw.g8.projectmanager.model.databaseModel.IssueLabel
import pt.isel.daw.g8.projectmanager.model.databaseModel.IssueLabelId
import pt.isel.daw.g8.projectmanager.model.databaseModel.Label
import pt.isel.daw.g8.projectmanager.model.databaseModel.ProjectAvailableLabelId
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.BadRequestException
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.ConflictException
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.ForbiddenException
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.NotFoundException
import pt.isel.daw.g8.projectmanager.model.inputModel.IssueLabelInput
import pt.isel.daw.g8.projectmanager.model.outputModel.EmptyResponseEntity
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.IssueLabelCollectionOutput
import pt.isel.daw.g8.projectmanager.repository.IssueLabelRepo
import pt.isel.daw.g8.projectmanager.repository.IssueRepo
import pt.isel.daw.g8.projectmanager.repository.ProjectAvailableLabelRepo
import pt.isel.daw.g8.projectmanager.services.interfaces.IssueLabelService

class IssueLabelServiceImpl(private val issueRepo: IssueRepo,
                            private val projectAvailableLabelRepo: ProjectAvailableLabelRepo,
                            private val issueLabelRepo: IssueLabelRepo) : IssueLabelService {

    override fun addIssueLabel(authUsername: String, issueLabel: IssueLabelInput): EmptyResponseEntity {
        if(!issueRepo.existsById(issueLabel.issueId))
            throw NotFoundException("There isn't a issue with id = '${issueLabel.issueId}'.")

        val issueDb = issueRepo.findById(issueLabel.issueId).get()
        if(issueDb.issueCreator != authUsername)
            throw ForbiddenException("Authentication credentials are not valid to make changes to this resource!")

        val label = Label(issueLabel.labelName)
        val projectAvailableLabelId = ProjectAvailableLabelId(issueDb.project, label)
        if(!projectAvailableLabelRepo.existsById(projectAvailableLabelId))
            throw BadRequestException("This label is not allowed for issues of this project.")

        val issueLabelId = IssueLabelId(issueDb.project, issueDb, label)
        if(issueLabelRepo.existsById(issueLabelId))
            throw ConflictException("This label already exists for this issue.")

        val issueLabelDb = IssueLabel(issueLabelId)
        issueLabelRepo.save(issueLabelDb)

        return EmptyResponseEntity(HttpStatus.CREATED)
    }

    override fun getIssueLabels(issueId: Int): OutputModel {
        if(!issueRepo.existsById(issueId))
            throw NotFoundException("There isn't a issue with id = '$issueId'.")
        val issueDb = issueRepo.findById(issueId).get()
        return IssueLabelCollectionOutput(issueDb.projectName, issueId, issueDb.labels).toSiren()
    }

    override fun deleteIssueLabel(authUsername: String, issueId: Int, labelName: String): EmptyResponseEntity {
        if(!issueRepo.existsById(issueId))
            throw NotFoundException("There isn't a issue with id = '$issueId'.")
        val issueDb = issueRepo.findById(issueId).get()

        if(issueDb.issueCreator != authUsername)
            throw ForbiddenException("Authentication credentials are not valid to make changes to this resource!")

        val projectDb = issueDb.project
        val labelDb = Label(labelName)

        val issueLabelId = IssueLabelId(projectDb, issueDb, labelDb)
        if(!issueLabelRepo.existsById(issueLabelId))
            throw NotFoundException("Issue Label doesn't exist.")

        issueLabelRepo.deleteById(issueLabelId)

        return EmptyResponseEntity(HttpStatus.OK)
    }
}