import parser from 'uri-template'

export default class Paths {
  static getHost () { return 'http://localhost:8090' }
  static getPaths () { return Paths.paths }
  static setPaths (paths) { Paths.paths = paths }

  static usersUrl () {
    return Paths.buildUri(Paths.paths.users_url)
  }

  static authenticateUrl () {
    return Paths.buildUri(Paths.paths.authenticate_url)
  }

  static userUrl (username) {
    return Paths.buildUri(Paths.paths.user_url, { username: username })
  }

  static userProjectsUrl (username) {
    return Paths.buildUri(Paths.paths.user_projects_url, { username: username })
  }

  static projectUrl (projectName) {
    return Paths.buildUri(Paths.paths.project_url, { projectName: projectName })
  }

  static projectLabelsUrl (projectName, labelName) {
    return Paths.buildUri(Paths.paths.project_labels_url, { projectName: projectName, labelName: labelName })
  }

  static projectStatesUrl (projectName, stateName) {
    return Paths.buildUri(Paths.paths.project_states_url, { projectName: projectName, stateName: stateName })
  }

  static projectStateTransitionsUrl (projectName, fromState, toState) {
    return Paths.buildUri(Paths.paths.project_state_transitions_url, { projectName: projectName, fromState: fromState, toState: toState })
  }

  static projectIssuesUrl (projectName) {
    return Paths.buildUri(Paths.paths.project_issues_url, { projectName: projectName })
  }

  static issueUrl (issueId) {
    return Paths.buildUri(Paths.paths.issue_url, { issueId: issueId })
  }

  static issueCommentsUrl (issueId) {
    return Paths.buildUri(Paths.paths.issue_comments_url, { issueId: issueId })
  }

  static issueCommentUrl (commentId) {
    return Paths.buildUri(Paths.paths.issue_comment_url, { commentId: commentId })
  }

  static issueLabelsUrl (issueId, labelName) {
    return Paths.buildUri(Paths.paths.issue_labels_url, { issueId: issueId, labelName: labelName })
  }

  static buildUri (template, templateVariables) {
    const parsedUri = parser.parse(template)
    return parsedUri.expand(templateVariables)
  }
}

Paths.paths = {}
