package pt.isel.daw.g8.projectmanager.repository

import org.springframework.data.repository.CrudRepository
import pt.isel.daw.g8.projectmanager.model.databaseModel.ProjectIssueLabel
import pt.isel.daw.g8.projectmanager.model.databaseModel.ProjectIssueLabelId

interface ProjectIssueLabelRepo : CrudRepository<ProjectIssueLabel, ProjectIssueLabelId>