package pt.isel.daw.g8.projectmanager

object ProjectPaths {

    /**
     * PATHS PLACEHOLDERS
     */
    const val USERNAME_VAR = "username"
    const val PROJECT_NAME_VAR = "projectName"
    const val LABEL_NAME_VAR = "labelName"
    const val STATE_NAME_VAR = "stateName"
    const val ISSUE_ID_VAR = "issueId"
    const val COMMENT_ID_VAR = "commentId"

    /**
     * PATHS
     */
    /****************************** USER PATHS ******************************/
    const val USERS = "/users"

    /****************************** PROJECT PATHS ******************************/
    const val PROJECTS = "/projects"

    /****************************** LABEL PATHS ******************************/
    const val LABELS = "$PROJECTS/available-labels"

    /****************************** STATE PATHS ******************************/
    const val STATES = "$PROJECTS/available-states"

    /****************************** STATE-TRANSITIONS PATHS ******************************/
    const val STATE_TRANSITIONS = "$PROJECTS/state-transitions"

    /****************************** ISSUE PATHS ******************************/
    const val ISSUES = "/issues"

    /****************************** ISSUE COMMENT PATHS ******************************/
    const val ISSUE_COMMENTS = "$ISSUES/comments"

    /****************************** ISSUE LABEL PATHS ******************************/
    const val ISSUE_LABELS = "$ISSUES/labels"
}