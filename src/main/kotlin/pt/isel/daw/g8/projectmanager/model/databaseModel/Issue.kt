package pt.isel.daw.g8.projectmanager.model.databaseModel

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*
import javax.persistence.*

@Entity(name = "issue")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Issue(@Column(name = "creator_name") val creatorName : String,
                 @Column(name = "project_name") val projectName : String,
                 @Column(name = "issue_name") val name : String,
                 @Column(name = "description") val description : String,
                 @Column(name = "issue_state") val stateName : String,
                 @Column(name = "creation_date") val creationDate : Date = Date(),
                 @Column(name = "close_date") val closeDate : Date? = null) : DbModel {


    @ManyToOne
    @JoinColumn(name = "creator_name", insertable = false, updatable = false)
    lateinit var creator : UserInfo

    @ManyToOne
    @JoinColumn(name = "project_name", insertable = false, updatable = false)
    lateinit var project : Project

    @ManyToOne
    @JoinColumn(name="issue_state", insertable = false, updatable = false)
    lateinit var state : State

    @Id
    @Column(name = "issue_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int = 0

    @OneToMany(mappedBy = "projectIssueLabelId.issue")
    @JsonIgnore
    lateinit var labels : List<IssueLabel>

    @OneToMany(mappedBy = "issue")
    @JsonIgnore
    lateinit var comments : List<Comment>
}