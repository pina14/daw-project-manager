package pt.isel.daw.g8.projectmanager.services.interfaces

import pt.isel.daw.g8.projectmanager.model.inputModel.CreateIssueInput
import pt.isel.daw.g8.projectmanager.model.inputModel.UpdateIssueInput
import pt.isel.daw.g8.projectmanager.model.outputModel.EmptyResponseEntity
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel

interface IssueService {
    fun createIssue(issue: CreateIssueInput): EmptyResponseEntity
    fun getProjectIssues(projectName: String): OutputModel
    fun getIssueById(issueId : Int): OutputModel
    fun updateIssue(authUsername : String, issueId : Int, issue: UpdateIssueInput): EmptyResponseEntity
    fun deleteIssue(authUsername : String, issueId: Int): EmptyResponseEntity
}