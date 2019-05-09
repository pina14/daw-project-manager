package pt.isel.daw.g8.projectmanager.repository

import org.springframework.data.repository.CrudRepository
import pt.isel.daw.g8.projectmanager.model.databaseModel.IssueComment
import java.util.*

interface IssueCommentRepo : CrudRepository<IssueComment, Int> {
    fun findAllByIssueId(issueId : Int) : Optional<List<IssueComment>>
}