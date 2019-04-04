package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.Project
import pt.isel.daw.g8.projectmanager.model.outputModel.mediaType.SirenModel

class ProjectsOutput(val username : String, val projects : List<Project>) : EntityRepresentation {

    override fun toSiren(): SirenModel {
        return SirenModel(
                _class = arrayOf("Projects", "Collection"),
                entities = getEntities(),
                links = getLinks())
    }

    private fun getEntities(): Array<SirenModel.SirenEntity>? {
        val entities = Array(projects.size) {index ->
            val project = projects[index]
            SirenModel.SirenEmbeddedRepresentation(
                    _class = arrayOf("Project"),
                    rel = arrayOf("/rels/project"),
                    properties = ProjectOutput(project),
                    links = arrayOf(SirenModel.SirenLink(arrayOf("self"), "/users/$username/projects/${project.name}"))
            )
        }

        return entities as Array<SirenModel.SirenEntity>
    }

    private fun getLinks(): Array<SirenModel.SirenLink>? {
        val selfLink = SirenModel.SirenLink(arrayOf("self"), "/users/$username/projects")
        return arrayOf(selfLink)
    }
}