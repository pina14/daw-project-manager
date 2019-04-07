package pt.isel.daw.g8.projectmanager.model.inputModel

import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel

class IssueCommentInput(val issueId : Int, val commentCreator : String, val content : String) : InputModel {

    companion object {
        fun getSirenActionFields() : Array<SirenModel.SirenActionField>? {
            val issueIdField = SirenModel.SirenActionField(name = "issueId", type = "number")
            val commentCreatorField = SirenModel.SirenActionField(name = "commentCreator", type = "text")
            val contentField = SirenModel.SirenActionField(name = "content", type = "text")

            return arrayOf(issueIdField, commentCreatorField, contentField)
        }
    }
}