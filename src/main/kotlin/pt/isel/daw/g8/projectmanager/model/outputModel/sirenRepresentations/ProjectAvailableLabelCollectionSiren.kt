package pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations

import org.springframework.http.HttpMethod
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.model.inputModel.ProjectAvailableLabelInput
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.ProjectAvailableLabelCollectionOutput
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.ProjectAvailableLabelOutput

class ProjectAvailableLabelCollectionSiren(override val entity : ProjectAvailableLabelCollectionOutput) : SirenRepresentation {
    override fun getClasses(): Array<String>? = arrayOf("Collection", "Project Available Label")

    override fun getProperties(): EntityRepresentation? = entity

    override fun getEntities(): Array<SirenModel.SirenEntity>? {
        val availableLabels = entity.availableLabels
        return Array(availableLabels.size) {index ->
            val availableLabel = availableLabels[index]
            SirenModel.SirenEmbeddedRepresentation(
                    _class = arrayOf("Project Available Label"),
                    rel = arrayOf("/rels/project-available-label"),
                    properties = ProjectAvailableLabelOutput(availableLabel)
            )
        }
    }

    override fun getActions(): Array<SirenModel.SirenAction>? {
        val addProjectAvailableLabelAction = SirenModel.SirenAction(
                name = "add-project-available-label",
                title = "Add Project Available Label",
                method = HttpMethod.POST.name,
                href = ProjectPaths.PROJECT_LABELS,
                type = "application/json",
                fields = ProjectAvailableLabelInput.getSirenActionFields()
        )

        return arrayOf(addProjectAvailableLabelAction)
    }

    override fun getLinks(): Array<SirenModel.SirenLink>? {
        val selfLink = SirenModel.SirenLink(arrayOf("self"), "${ProjectPaths.PROJECT_LABELS}?${ProjectPaths.PROJECT_NAME_VAR}=${entity.projectName}")
        return arrayOf(selfLink)
    }
}