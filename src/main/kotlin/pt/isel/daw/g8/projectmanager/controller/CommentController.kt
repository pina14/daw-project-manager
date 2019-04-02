package pt.isel.daw.g8.projectmanager.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import pt.isel.daw.g8.projectmanager.ProjectPaths
import pt.isel.daw.g8.projectmanager.middleware.RequiresAuthentication
import pt.isel.daw.g8.projectmanager.model.outputModel.OutputModel
import pt.isel.daw.g8.projectmanager.repository.CommentRepo
import pt.isel.daw.g8.projectmanager.repository.IssueRepo
import pt.isel.daw.g8.projectmanager.repository.ProjectRepo
import sun.reflect.generics.reflectiveObjects.NotImplementedException

@RestController
@RequestMapping(ProjectPaths.COMMENTS)
class CommentController {

    @Autowired lateinit var projectRepo : ProjectRepo
    @Autowired lateinit var issueRepo: IssueRepo
    @Autowired lateinit var commentRepo: CommentRepo

    @PostMapping
    @RequiresAuthentication
    fun createIssueComment() {
        //TODO Implement and set parameters
        throw NotImplementedException()
    }

    @GetMapping
    fun getIssueComments(@PathVariable(ProjectPaths.ISSUE_ID_VAR) issueId: Int) : OutputModel {
        //TODO Implement and set parameters
        throw NotImplementedException()
    }

    @GetMapping(ProjectPaths.COMMENT_ID)
    fun getCommentById(@PathVariable(ProjectPaths.COMMENT_ID_VAR) commentId: Int) : OutputModel {
        //TODO Implement and set parameters
        throw NotImplementedException()
    }

    @PutMapping(ProjectPaths.COMMENT_ID)
    @RequiresAuthentication
    fun updateComment() {
        //TODO Implement and set parameters
        throw NotImplementedException()
    }

    @DeleteMapping(ProjectPaths.COMMENT_ID)
    @RequiresAuthentication
    fun deleteComment(@PathVariable(ProjectPaths.COMMENT_ID_VAR) commentId: Int) : OutputModel {
        //TODO Implement and set parameters
        throw NotImplementedException()
    }
}
