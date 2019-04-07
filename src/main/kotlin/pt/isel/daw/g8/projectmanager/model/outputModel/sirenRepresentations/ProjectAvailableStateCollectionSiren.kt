package pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations

import org.springframework.http.HttpMethod
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.model.inputModel.ProjectAvailableStateInput
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.ProjectAvailableStateCollectionOutput
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.ProjectAvailableStateOutput

class ProjectAvailableStateCollectionSiren(override val entity : ProjectAvailableStateCollectionOutput) : SirenRepresentation {
    override fun getClasses(): Array<String>? = arrayOf("Collection", "Project Available State")

    override fun getProperties(): EntityRepresentation? = null

    override fun getEntities(): Array<SirenModel.SirenEntity>? {
        val availableStates = entity.availableStates
        return Array(availableStates.size) {index ->
            val availableState = availableStates[index]
            SirenModel.SirenEmbeddedRepresentation(
                    _class = arrayOf("Project Available State"),
                    rel = arrayOf("/rels/project-available-state"),
                    properties = ProjectAvailableStateOutput(availableState)
            )
        }
    }

    override fun getActions(): Array<SirenModel.SirenAction>? {
        val addProjectAvailableStateAction = SirenModel.SirenAction(
                name = "add-project-available-state",
                title = "Add Project Available State",
                method = HttpMethod.POST.name,
                href = ProjectPaths.PROJECT_STATES,
                type = "application/json",
                fields = ProjectAvailableStateInput.getSirenActionFields()
        )

        return arrayOf(addProjectAvailableStateAction)
    }

    override fun getLinks(): Array<SirenModel.SirenLink>? {
        val selfLink = SirenModel.SirenLink(arrayOf("self"), "${ProjectPaths.PROJECT_STATES}?${ProjectPaths.PROJECT_NAME_VAR}=${entity.projectName}")
        return arrayOf(selfLink)
    }
}