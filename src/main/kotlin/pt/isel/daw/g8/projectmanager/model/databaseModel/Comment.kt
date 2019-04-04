package pt.isel.daw.g8.projectmanager.model.databaseModel

import java.util.*
import javax.persistence.*

@Entity(name = "issue_comment")
data class Comment(
        @Id @Column(name = "comment_id") val id : Int,
        @ManyToOne @JoinColumn(name = "issue_id") val issue : Issue,
        @Column(name = "content") val content : String,
        @Column(name = "creation_date") val creationDate : Date) : DbModel