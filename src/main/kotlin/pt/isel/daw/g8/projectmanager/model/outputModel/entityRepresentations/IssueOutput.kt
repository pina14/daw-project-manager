package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.Issue
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations.IssueSiren
import java.util.*

class IssueOutput(issue : Issue) : EntityRepresentation {
    var id : Int? = null
    var issueCreator : String? = null
    var projectName : String? = null
    var issueName : String? = null
    var description : String? = null
    var creationDate : Date? = null
    var closeDate : Date? = null
    var state : String? = null

    init {
        id = issue.id
        issueCreator = issue.issueCreator
        projectName = issue.projectName
        issueName = issue.name
        description = issue.description
        state = issue.stateName
        creationDate = issue.creationDate
        closeDate = issue.closeDate
    }

    override fun toSiren(): SirenModel = SirenModel(IssueSiren(this))
}
