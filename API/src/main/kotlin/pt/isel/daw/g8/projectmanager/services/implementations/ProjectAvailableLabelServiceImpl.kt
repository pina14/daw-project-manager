package pt.isel.daw.g8.projectmanager.services.implementations

import org.springframework.http.HttpStatus
import pt.isel.daw.g8.projectmanager.model.databaseModel.Label
import pt.isel.daw.g8.projectmanager.model.databaseModel.ProjectAvailableLabel
import pt.isel.daw.g8.projectmanager.model.databaseModel.ProjectAvailableLabelId
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.ConflictException
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.ForbiddenException
import pt.isel.daw.g8.projectmanager.model.errorModel.errorRepresentations.NotFoundException
import pt.isel.daw.g8.projectmanager.model.inputModel.ProjectAvailableLabelInput
import pt.isel.daw.g8.projectmanager.model.outputModel.EmptyResponseEntity
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.ProjectAvailableLabelCollectionOutput
import pt.isel.daw.g8.projectmanager.repository.LabelRepo
import pt.isel.daw.g8.projectmanager.repository.ProjectAvailableLabelRepo
import pt.isel.daw.g8.projectmanager.repository.ProjectRepo
import pt.isel.daw.g8.projectmanager.services.interfaces.ProjectAvailabeLabelService

class ProjectAvailableLabelServiceImpl(private val projectRepo: ProjectRepo,
                                       private val labelRepo : LabelRepo,
                                       private val projectAvailableLabelRepo : ProjectAvailableLabelRepo) : ProjectAvailabeLabelService {

    override fun addProjectAvailableLabel(authUsername: String, projectAvailableLabel: ProjectAvailableLabelInput): EmptyResponseEntity {
        val projectName = projectAvailableLabel.projectName
        if(!projectRepo.existsById(projectName))
            throw NotFoundException("Doesn't exist a project with name '$projectName'.")

        val projectDb = projectRepo.findById(projectName).get()
        if(authUsername != projectDb.username)
            throw ForbiddenException("Authentication credentials are not valid to make changes to this resource!")

        val label = Label(projectAvailableLabel.labelName)
        if(!labelRepo.existsById(label.labelName))
            labelRepo.save(label)

        val projectAvailableLabelId = ProjectAvailableLabelId(projectDb, label)
        if(projectAvailableLabelRepo.existsById(projectAvailableLabelId))
            throw ConflictException("This label already exists for this project.")

        val projectAvailableLabelDb = ProjectAvailableLabel(projectAvailableLabelId)

        projectAvailableLabelRepo.save(projectAvailableLabelDb)

        return EmptyResponseEntity(HttpStatus.CREATED)
    }

    override fun getProjectAvailableLabels(projectName: String): OutputModel {
        if(!projectRepo.existsById(projectName))
            throw NotFoundException("There isn't a project with name = '$projectName'.")

        val projectDb = projectRepo.findById(projectName).get()
        return ProjectAvailableLabelCollectionOutput(projectName, projectDb.availableLabels).toSiren()
    }

    override fun deleteProjectAvailableLabel(authUsername: String, projectName: String, labelName: String): EmptyResponseEntity {
        if(!projectRepo.existsById(projectName))
            throw NotFoundException("There isn't a project with name '$projectName'.")
        val projectDb = projectRepo.findById(projectName).get()

        if(projectDb.username != authUsername)
            throw ForbiddenException("Authentication credentials are not valid to make changes to this resource!")

        val labelDb = Label(labelName)

        val projectAvailableLabelId = ProjectAvailableLabelId(projectDb, labelDb)
        if(!projectAvailableLabelRepo.existsById(projectAvailableLabelId))
            throw NotFoundException("Project Label doesn't exist.")

        projectAvailableLabelRepo.deleteById(projectAvailableLabelId)

        return EmptyResponseEntity(HttpStatus.OK)
    }
}