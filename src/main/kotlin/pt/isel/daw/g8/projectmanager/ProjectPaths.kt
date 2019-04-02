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
    const val USER_ID = "/$USERNAME_VAR"

    /****************************** PROJECT PATHS ******************************/
    const val PROJECTS = "$USER_ID/projects"
    const val PROJECT_ID = "/$PROJECT_NAME_VAR"

    /****************************** LABEL PATHS ******************************/
    const val LABELS = "$PROJECT_ID/labels"
    const val LABEL_ID = "/$LABEL_NAME_VAR"

    /****************************** STATE PATHS ******************************/
    const val STATES = "$PROJECT_ID/states"
    const val STATE_ID = "/$STATE_NAME_VAR"

    /****************************** ISSUE PATHS ******************************/
    const val ISSUES = "$PROJECT_ID/issues"
    const val ISSUE_ID = "/$ISSUE_ID_VAR"

    /****************************** ISSUE COMMENT PATHS ******************************/
    const val COMMENTS = "$ISSUE_ID/comments"
    const val COMMENT_ID = "/$COMMENT_ID_VAR"

    /****************************** ISSUE LABEL PATHS ******************************/
    const val ISSUE_LABELS = "$ISSUE_ID/labels"
    const val ISSUE_LABEL_ID = "/$LABEL_NAME_VAR"
}