package pt.isel.daw.g8.projectmanager.services.interfaces

import org.springframework.http.ResponseEntity
import pt.isel.daw.g8.projectmanager.model.inputModel.IssueLabelInput
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel

interface IssueLabelService {
    fun addIssueLabel(authUsername: String, issueLabel: IssueLabelInput): ResponseEntity<Unit>
    fun getIssueLabels(issueId: Int): OutputModel
    fun deleteIssueLabel(authUsername : String, issueId: Int, labelName : String): ResponseEntity<Unit>
}