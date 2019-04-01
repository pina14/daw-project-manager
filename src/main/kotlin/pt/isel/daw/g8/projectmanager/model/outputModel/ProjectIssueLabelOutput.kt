package pt.isel.daw.g8.projectmanager.model.outputModel

import pt.isel.daw.g8.projectmanager.model.databaseModel.ProjectIssueLabel

class ProjectIssueLabelOutput(issueLabel : ProjectIssueLabel) : OutputModel {
    var projectName : String? = null
    var issueId : Int? = null
    var labelName : String? = null

    init {
        projectName = issueLabel.projectIssueLabelId.project.name
        issueId = issueLabel.projectIssueLabelId.issue.id
        labelName = issueLabel.projectIssueLabelId.label.labelName
    }
}
