package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.IssueLabel

class IssueLabelOutput(issueLabel : IssueLabel) : EntityRepresentation {
    var projectName : String? = null
    var issueId : Int? = null
    var labelName : String? = null

    init {
        projectName = issueLabel.projectIssueLabelId.project.name
        issueId = issueLabel.projectIssueLabelId.issue.id
        labelName = issueLabel.projectIssueLabelId.label.labelName
    }
}
