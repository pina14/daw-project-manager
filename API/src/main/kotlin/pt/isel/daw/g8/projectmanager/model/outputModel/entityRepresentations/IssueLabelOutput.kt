package pt.isel.daw.g8.projectmanager.model.outputModel.entityRepresentations

import pt.isel.daw.g8.projectmanager.model.databaseModel.IssueLabel
import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel
import pt.isel.daw.g8.projectmanager.model.outputModel.sirenRepresentations.IssueLabelSiren

class IssueLabelOutput(issueLabel : IssueLabel) : EntityRepresentation {

    var projectName : String? = null
    var issueId : Int? = null
    var labelName : String? = null

    init {
        projectName = issueLabel.projectIssueLabelId.project.name
        issueId = issueLabel.projectIssueLabelId.issue.id
        labelName = issueLabel.projectIssueLabelId.label.labelName
    }

    override fun toSiren(): SirenModel = SirenModel(IssueLabelSiren(this))
}
