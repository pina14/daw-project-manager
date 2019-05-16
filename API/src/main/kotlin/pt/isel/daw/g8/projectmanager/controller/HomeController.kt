package pt.isel.daw.g8.projectmanager.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class HomeController : ProjectManagerController {

    @GetMapping(produces = ["application/json"])
    fun getIssueComments() : String = "{\n" +
            "\t\"users_url\": \"/users\",\n" +
            "\t\"authenticate_url\": \"/users/authenticate\",\n" +
            "\t\"user_url\": \"/users/{username}\",\n" +
            "\t\"user_projects_url\": \"/projects{?username}\",\n" +
            "\t\"project_url\": \"/projects/{projectName}\",\n" +
            "\t\"project_labels_url\": \"/projects/available-labels{?projectName,labelName}\",\n" +
            "\t\"project_states_url\": \"/projects/available-states{?projectName,stateName}\",\n" +
            "\t\"project_state_transitions_url\": \"/projects/state-transitions{?projectName,fromState,toState}\",\n" +
            "\t\"project_issues_url\": \"/issues{?projectName}\",\n" +
            "\t\"issue_url\": \"/issues/{issueId}\",\n" +
            "\t\"issue_comments_url\": \"/issues/comments{?issueId}\",\n" +
            "\t\"issue_comment_url\": \"/issues/comments/{commentId}\",\n" +
            "\t\"issue_labels_url\": \"/issues/labels{?issueId,labelName}\"\n" +
            "}"
}
