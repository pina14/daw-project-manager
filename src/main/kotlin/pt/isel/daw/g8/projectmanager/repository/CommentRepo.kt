package pt.isel.daw.g8.projectmanager.repository

import org.springframework.data.repository.CrudRepository
import pt.isel.daw.g8.projectmanager.model.databaseModel.Comment
import java.util.*

interface CommentRepo : CrudRepository<Comment, Int> {
    fun findAllByIssueId(issueId : Int) : Optional<List<Comment>>
}