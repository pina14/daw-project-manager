package pt.isel.daw.g8.projectmanager.model.databaseModel

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

@Entity(name = "issue")
data class Issue(
        @Id @Column(name = "issue_id") val id : Int,
        @ManyToOne @JoinColumn(name = "project_name") val project : Project,
        @Column(name = "issue_name") val name : String,
        @Column(name = "description") val description : String,
        @ManyToOne @JoinColumn(name="issue_state") val state : IssueState,
        @Column(name = "creation_date") val creationDate : Date,
        @Column(name = "close_date") val closeDate : Date?) {

    @OneToMany(mappedBy = "projectIssueLabelId.issue")
    @JsonIgnore
    lateinit var labels : List<ProjectIssueLabel>

    @OneToMany(mappedBy = "issue")
    @JsonIgnore
    lateinit var comments : List<Comment>
}