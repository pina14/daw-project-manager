import UriBuilder from './uri-builder'

export default class ApiPaths {
  static getHost () { return 'http://localhost:8090' }
  static getPaths () { return ApiPaths.paths }
  static setPaths (paths) { ApiPaths.paths = paths }

  static usersUrl () {
    return UriBuilder.build(ApiPaths.paths.users_url)
  }

  static authenticateUrl () {
    return UriBuilder.build(ApiPaths.paths.authenticate_url)
  }

  static userUrl (username) {
    return UriBuilder.build(ApiPaths.paths.user_url, { username: username })
  }

  static userProjectsUrl (username) {
    return UriBuilder.build(ApiPaths.paths.user_projects_url, { username: username })
  }

  static projectUrl (projectName) {
    return UriBuilder.build(ApiPaths.paths.project_url, { projectName: projectName })
  }

  static projectLabelsUrl (projectName, labelName) {
    return UriBuilder.build(ApiPaths.paths.project_labels_url, { projectName: projectName, labelName: labelName })
  }

  static projectStatesUrl (projectName, stateName) {
    return UriBuilder.build(ApiPaths.paths.project_states_url, { projectName: projectName, stateName: stateName })
  }

  static projectStateTransitionsUrl (projectName, fromState, toState) {
    return UriBuilder.build(ApiPaths.paths.project_state_transitions_url, { projectName: projectName, fromState: fromState, toState: toState })
  }

  static projectIssuesUrl (projectName) {
    return UriBuilder.build(ApiPaths.paths.project_issues_url, { projectName: projectName })
  }

  static issueUrl (issueId) {
    return UriBuilder.build(ApiPaths.paths.issue_url, { issueId: issueId })
  }

  static issueCommentsUrl (issueId) {
    return UriBuilder.build(ApiPaths.paths.issue_comments_url, { issueId: issueId })
  }

  static issueCommentUrl (commentId) {
    return UriBuilder.build(ApiPaths.paths.issue_comment_url, { commentId: commentId })
  }

  static issueLabelsUrl (issueId, labelName) {
    return UriBuilder.build(ApiPaths.paths.issue_labels_url, { issueId: issueId, labelName: labelName })
  }
}

ApiPaths.paths = {}
