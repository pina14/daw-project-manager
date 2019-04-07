package pt.isel.daw.g8.projectmanager

object ProjectPaths {

    /**
     * PATHS PLACEHOLDERS
     */
    const val USERNAME_VAR = "username"
    const val PROJECT_NAME_VAR = "projectName"
    const val LABEL_NAME_VAR = "labelName"
    const val STATE_NAME_VAR = "stateName"
    const val FROM_STATE_VAR = "fromState"
    const val TO_STATE_VAR = "toState"
    const val ISSUE_ID_VAR = "issueId"
    const val COMMENT_ID_VAR = "commentId"

    /**
     * PATHS
     */
    /****************************** USERS PATHS ******************************/
    const val USERS = "/users"
    const val USER_ID = "/{$USERNAME_VAR}"

    /****************************** PROJECTS PATHS ******************************/
    const val PROJECTS = "/projects"
    const val PROJECT_ID = "/{$PROJECT_NAME_VAR}"

    /****************************** PROJECT AVAILABLE LABELS PATHS ******************************/
    const val PROJECT_LABELS = "$PROJECTS/available-labels"

    /****************************** PROJECT AVAILABLE STATES PATHS ******************************/
    const val PROJECT_STATES = "$PROJECTS/available-states"

    /****************************** PROJECT STATE TRANSITIONS PATHS ******************************/
    const val PROJECT_STATE_TRANSITIONS = "$PROJECTS/state-transitions"

    /****************************** ISSUES PATHS ******************************/
    const val ISSUES = "/issues"
    const val ISSUE_ID = "/{$ISSUE_ID_VAR}"

    /****************************** ISSUE COMMENTS PATHS ******************************/
    const val ISSUE_COMMENTS = "$ISSUES/comments"
    const val ISSUE_COMMENT_ID = "/{$COMMENT_ID_VAR}"

    /****************************** ISSUE LABELS PATHS ******************************/
    const val ISSUE_LABELS = "$ISSUES/labels"
}