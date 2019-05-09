package pt.isel.daw.g8.projectmanager.model.inputModel

import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel

class CreateIssueInput(val issueCreator : String,
                       val projectName : String,
                       val issueName : String,
                       val description: String) : InputModel {

    companion object {
        fun getSirenActionFields() : Array<SirenModel.SirenActionField>? {
            val issueCreatorField = SirenModel.SirenActionField(name = "issueCreator", type = "text")
            val projectNameField = SirenModel.SirenActionField(name = "projectName", type = "text")
            val issueNameField = SirenModel.SirenActionField(name = "issueName", type = "text")
            val descriptionField = SirenModel.SirenActionField(name = "description", type = "text")

            return arrayOf(issueCreatorField, projectNameField, issueNameField, descriptionField)
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