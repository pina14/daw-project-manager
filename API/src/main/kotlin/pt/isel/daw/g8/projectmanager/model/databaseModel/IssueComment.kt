package pt.isel.daw.g8.projectmanager.model.databaseModel

import java.util.*
import javax.persistence.*

@Entity(name = "issue_comment")
data class IssueComment(@Column(name = "comment_creator") val commentCreator : String,
                        @Column(name = "issue_id") val issueId : Int,
                        @Column(name = "content") val content : String) : DbModel {

    @ManyToOne
    @JoinColumn(name = "comment_creator", insertable = false, updatable = false)
    lateinit var creator : User

    @ManyToOne
    @JoinColumn(name = "issue_id", insertable = false, updatable = false)
    lateinit var issue : Issue

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int = 0

    @Column(name = "creation_date")
    val creationDate : Date = Date()
}