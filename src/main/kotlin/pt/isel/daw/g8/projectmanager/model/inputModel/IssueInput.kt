package pt.isel.daw.g8.projectmanager.model.inputModel

import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel

class CreateIssueInput(val creatorName : String,
                       val projectName : String,
                       val issueName : String,
                       val description: String) : InputModel {

    companion object {
        fun getSirenActionFields() : Array<SirenModel.SirenActionField>? {
            val creatorNameField = SirenModel.SirenActionField(name = "creatorName", type = "text")
            val projectNameField = SirenModel.SirenActionField(name = "projectName", type = "text")
            val issueNameField = SirenModel.SirenActionField(name = "issueName", type = "text")
            val descriptionField = SirenModel.SirenActionField(name = "description", type = "text")

            return arrayOf(creatorNameField, projectNameField, issueNameField, descriptionField)
        }
    }
}

class UpdateIssueInput(val issueName : String,
                       val description : String,
                       val state : String) : InputModel {

    companion object {
        fun getSirenActionFields() : Array<SirenModel.SirenActionField>? {
            val issueNameField = SirenModel.SirenActionField(name = "issueName", type = "text")
            val descriptionField = SirenModel.SirenActionField(name = "description", type = "text")
            val stateField = SirenModel.SirenActionField(name = "state", type = "text")

            return arrayOf(issueNameField, descriptionField, stateField)
        }
    }
}