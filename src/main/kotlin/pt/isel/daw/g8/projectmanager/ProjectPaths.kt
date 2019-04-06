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
    const val USER_ID = "/{$USERNAME_VAR}"

    /****************************** PROJECT PATHS ******************************/
    const val PROJECTS = "/projects"
    const val PROJECT_ID = "/{$PROJECT_NAME_VAR}"
    private const val FULL_PROJECT_PATH = "$PROJECTS$PROJECT_ID"

    /****************************** LABEL PATHS ******************************/
    const val LABELS = "$FULL_PROJECT_PATH/labels"
    const val LABEL_ID = "/{$LABEL_NAME_VAR}"

    /****************************** STATE PATHS ******************************/
    const val STATES = "$FULL_PROJECT_PATH/states"
    const val STATE_ID = "/{$STATE_NAME_VAR}"

    /****************************** STATE-TRANSITIONS PATHS ******************************/
    const val STATE_TRANSITIONS = "$FULL_PROJECT_PATH/state-transitions"
    const val STATE_TRANSITIONS_ID = "/{$STATE_NAME_VAR}"

    /****************************** ISSUE PATHS ******************************/
    const val ISSUES = "/issues"
    const val ISSUE_ID = "/{$ISSUE_ID_VAR}"
    private const val FULL_ISSUE_PATH = "$ISSUES$ISSUE_ID"

    /****************************** ISSUE COMMENT PATHS ******************************/
    const val COMMENTS = "$FULL_ISSUE_PATH/comments"
    const val COMMENT_ID = "/{$COMMENT_ID_VAR}"

    /****************************** ISSUE LABEL PATHS ******************************/
    const val ISSUE_LABELS = "$FULL_ISSUE_PATH/labels"
    const val ISSUE_LABEL_ID = "/{$LABEL_NAME_VAR}"
}