package pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations

import org.springframework.http.HttpMethod
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.model.inputModel.CreateProjectInput
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.EntityRepresentation
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.ProjectCollectionOutput
import pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations.ProjectOutput

class ProjectCollectionSiren(override val entity : ProjectCollectionOutput) : SirenRepresentation {
    override fun getClasses(): Array<String>? = arrayOf("Collection", "Project")

    override fun getProperties(): EntityRepresentation? = entity

    override fun getEntities(): Array<SirenModel.SirenEntity>? {
        val projects = entity.projects
        return Array(projects.size) {index ->
            val project = projects[index]
            SirenModel.SirenEmbeddedRepresentation(
                    _class = arrayOf("Project"),
                    rel = arrayOf("/rels/project"),
                    properties = ProjectOutput(project),
                    links = arrayOf(SirenModel.SirenLink(arrayOf("self"), "${ProjectPaths.PROJECTS}/${project.name}"))
            )
        }
    }

    override fun getActions(): Array<SirenModel.SirenAction>? {
        val createProjectAction = SirenModel.SirenAction(
                name = "create-project",
                title = "Create Project",
                method = HttpMethod.POST.name,
                href = ProjectPaths.PROJECTS,
                type = "application/json",
                fields = CreateProjectInput.getSirenActionFields()
        )

        return arrayOf(createProjectAction)

    }

    override fun getLinks(): Array<SirenModel.SirenLink>? {
        val selfLink = SirenModel.SirenLink(arrayOf("self"), ProjectPaths.PROJECTS)
        return arrayOf(selfLink)
    }
}