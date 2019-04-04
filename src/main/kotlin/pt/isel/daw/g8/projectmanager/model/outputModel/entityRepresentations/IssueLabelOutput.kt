package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.IssueLabel
import pt.isel.daw.g8.projectmanager.model.outputModel.mediaType.SirenModel

class IssueLabelOutput(issueLabel : IssueLabel) : EntityRepresentation {

    var projectName : String? = null
    var issueId : Int? = null
    var labelName : String? = null

    init {
        projectName = issueLabel.projectIssueLabelId.project.name
        issueId = issueLabel.projectIssueLabelId.issue.id
        labelName = issueLabel.projectIssueLabelId.label.labelName
    }

    override fun toSiren(): SirenModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
