package pt.isel.daw.g8.projectmanager.model.inputModel

class CreateUserInfoInput(val username : String,
                          val password : String,
                          val email : String,
                          val fullName : String) : InputModel

class UpdateUserInfoInput(val email : String, val fullName : String) : InputModel

