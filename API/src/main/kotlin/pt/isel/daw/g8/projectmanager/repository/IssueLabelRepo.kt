package pt.isel.daw.g8.projectmanager.repository

import org.springframework.data.repository.CrudRepository
import pt.isel.daw.g8.projectmanager.model.databaseModel.IssueLabel
import pt.isel.daw.g8.projectmanager.model.databaseModel.IssueLabelId

interface IssueLabelRepo : CrudRepository<IssueLabel, IssueLabelId>