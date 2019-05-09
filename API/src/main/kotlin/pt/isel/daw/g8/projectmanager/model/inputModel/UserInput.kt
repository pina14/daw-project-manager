package pt.isel.daw.g8.projectmanager.model.inputModel

import pt.isel.daw.g8.projectmanager.model.outputModel.SirenModel

class CreateUserInput(val username : String,
                      val password : String,
                      val email : String,
                      val fullName : String) : InputModel

class UpdateUserInput(val email : String, val fullName : String) : InputModel {

    companion object {
        fun getSirenActionFields() : Array<SirenModel.SirenActionField>? {
            val emailField = SirenModel.SirenActionField(name = "email", type = "text")
            val fullNameField = SirenModel.SirenActionField(name = "fullName", type = "text")

            return arrayOf(emailField, fullNameField)
        }
    }
}

