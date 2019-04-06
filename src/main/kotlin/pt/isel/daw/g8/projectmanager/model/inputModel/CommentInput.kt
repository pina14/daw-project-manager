package pt.isel.daw.g8.projectmanager.model.inputModel

import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel

class CommentInput(val commentCreator : String, val content : String) : InputModel {

    companion object {
        fun getSirenActionFields() : Array<SirenModel.SirenActionField>? {
            val commentCreatorField = SirenModel.SirenActionField(name = "commentCreator", type = "text")
            val contentField = SirenModel.SirenActionField(name = "content", type = "text")

            return arrayOf(commentCreatorField, contentField)
        }
    }
}