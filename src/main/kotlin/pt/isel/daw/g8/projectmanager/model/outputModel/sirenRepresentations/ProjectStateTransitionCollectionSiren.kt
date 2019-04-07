package pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations

import org.springframework.http.HttpMethod
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.model.inputModel.ProjectStateTransitionInput
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.ProjectStateTransitionCollectionOutput
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.ProjectStateTransitionOutput

class ProjectStateTransitionCollectionSiren(override val entity : ProjectStateTransitionCollectionOutput) : SirenRepresentation {
    override fun getClasses(): Array<String>? = arrayOf("Project State Transitions", "Collection")

    override fun getProperties(): EntityRepresentation? = entity

    override fun getEntities(): Array<SirenModel.SirenEntity>? {
        val availableTransitions = entity.availableTransitions
        return Array(availableTransitions.size) {index ->
            val availableTransition = availableTransitions[index]
            SirenModel.SirenEmbeddedRepresentation(
                    _class = arrayOf("Project State Transition"),
                    rel = arrayOf("/rels/project-state-transition"),
                    properties = ProjectStateTransitionOutput(availableTransition)
            )
        }
    }

    override fun getActions(): Array<SirenModel.SirenAction>? {
        val addProjectStateTransitionAction = SirenModel.SirenAction(
                name = "add-project-state-transition",
                title = "Add Project State Transition",
                method = HttpMethod.POST.name,
                href = ProjectPaths.PROJECT_STATE_TRANSITIONS,
                type = "application/json",
                fields = ProjectStateTransitionInput.getSirenActionFields()
        )

        return arrayOf(addProjectStateTransitionAction)
    }

    override fun getLinks(): Array<SirenModel.SirenLink>? {
        val selfLink = SirenModel.SirenLink(arrayOf("self"), "${ProjectPaths.PROJECT_STATE_TRANSITIONS}?${ProjectPaths.PROJECT_NAME_VAR}=${entity.projectName}")
        return arrayOf(selfLink)
    }
}