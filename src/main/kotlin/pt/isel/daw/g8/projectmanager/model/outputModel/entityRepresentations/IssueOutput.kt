package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.Issue
import pt.isel.daw.g8.projectmanager.model.outputModel.mediaType.SirenModel

class IssueOutput(issue : Issue) : EntityRepresentation {
    var id : Int? = null
    var name : String? = null
    var description : String? = null
    var creationDate : String? = null
    var closeDate : String? = null
    var state : String? = null

    init {
        id = issue.id
        name = issue.name
        description = issue.description
        creationDate = issue.creationDate.toString()
        closeDate = issue.closeDate?.toString()
        state = issue.state.stateName
    }

    override fun toSiren(): SirenModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
