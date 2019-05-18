import UriBuilder from './uri-builder'

export default class ClientPaths {
  /**
   * Home Templates
   */
  static homeTemplate () {
    return ClientPaths.paths.home.router_template
  }

  static homeTemplateFilled () {
    return UriBuilder.build(ClientPaths.paths.home.uri_template)
  }

  /**
   * Session Templates
   */
  static registerTemplate () {
    return ClientPaths.paths.register.router_template
  }

  static registerTemplateFilled () {
    return UriBuilder.build(ClientPaths.paths.register.uri_template)
  }

  static loginTemplate () {
    return ClientPaths.paths.login.router_template
  }

  static loginTemplateFilled () {
    return UriBuilder.build(ClientPaths.paths.login.uri_template)
  }

  static logoutTemplate () {
    return ClientPaths.paths.logout.router_template
  }

  static logoutTemplateFilled () {
    return UriBuilder.build(ClientPaths.paths.logout.uri_template)
  }

  /**
   * Profile Templates
   */
  static profileTemplate () {
    return ClientPaths.paths.profile.router_template
  }

  static profileTemplateFilled (username) {
    if (!username) username = '*'
    return UriBuilder.build(ClientPaths.paths.profile.uri_template, { username: username })
  }

  static profileUpdateTemplate () {
    return ClientPaths.paths.profile_update.router_template
  }

  static profileUpdateTemplateFilled (username) {
    if (!username) username = '*'
    return UriBuilder.build(ClientPaths.paths.profile_update.uri_template, { username: username })
  }

  /**
   * Project Templates
   */
  static userProjectsTemplate () {
    return ClientPaths.paths.user_projects.router_template
  }

  static userProjectsTemplateFilled (username) {
    if (!username) username = '*'
    return UriBuilder.build(ClientPaths.paths.user_projects.uri_template, { username: username })
  }

  static projectTemplate () {
    return ClientPaths.paths.project.router_template
  }

  static projectTemplateFilled (projectName) {
    if (!projectName) projectName = '*'
    return UriBuilder.build(ClientPaths.paths.project.uri_template, { projectName: projectName })
  }

  static projectUpdateTemplate () {
    return ClientPaths.paths.project_update.router_template
  }

  static projectUpdateTemplateFilled (projectName) {
    if (!projectName) projectName = '*'
    return UriBuilder.build(ClientPaths.paths.project_update.uri_template, { projectName: projectName })
  }

  static projectCreateTemplate () {
    return ClientPaths.paths.project_create.router_template
  }

  static projectCreateTemplateFilled (username) {
    if (!username) username = '*'
    return UriBuilder.build(ClientPaths.paths.project_create.uri_template, { username: username })
  }

  /**
   * Project Labels Templates
   */
  static projectLabelCreateTemplate () {
    return ClientPaths.paths.project_label_create.router_template
  }

  static projectLabelCreateTemplateFilled (projectName) {
    if (!projectName) projectName = '*'
    return UriBuilder.build(ClientPaths.paths.project_label_create.uri_template, { projectName: projectName })
  }

  /**
   * Project States Templates
   */
  static projectStateCreateTemplate () {
    return ClientPaths.paths.project_state_create.router_template
  }

  static projectStateCreateTemplateFilled (projectName) {
    if (!projectName) projectName = '*'
    return UriBuilder.build(ClientPaths.paths.project_state_create.uri_template, { projectName: projectName })
  }

  /**
   * Project State Transitions Templates
   */
  static projectStateTransitionCreateTemplate () {
    return ClientPaths.paths.project_state_transition_create.router_template
  }

  static projectStateTransitionCreateTemplateFilled (projectName) {
    if (!projectName) projectName = '*'
    return UriBuilder.build(ClientPaths.paths.project_state_transition_create.uri_template, { projectName: projectName })
  }

  /**
   * Issue Templates
   */
  static issueTemplate () {
    return ClientPaths.paths.issue.router_template
  }

  static issueTemplateFilled (issueId) {
    if (!issueId) issueId = '*'
    return UriBuilder.build(ClientPaths.paths.issue.uri_template, { issueId: issueId })
  }

  static issueCreateTemplate () {
    return ClientPaths.paths.issue_create.router_template
  }

  static issueCreateTemplateFilled (projectName) {
    if (!projectName) projectName = '*'
    return UriBuilder.build(ClientPaths.paths.issue_create.uri_template, { projectName: projectName })
  }

  static issueUpdateTemplate () {
    return ClientPaths.paths.issue_update.router_template
  }

  static issueUpdateTemplateFilled (issueId) {
    if (!issueId) issueId = '*'
    return UriBuilder.build(ClientPaths.paths.issue_update.uri_template, { issueId: issueId })
  }
}

ClientPaths.paths = {
  home: { router_template: '/', uri_template: '/' },
  register: { router_template: '/register', uri_template: '/register' },
  login: { router_template: '/login', uri_template: '/login' },
  logout: { router_template: '/logout', uri_template: '/logout' },
  profile: { router_template: '/profile/:username', uri_template: '/profile/{username}' },
  profile_update: { router_template: '/profile/:username/update', uri_template: '/profile/{username}/update' },
  user_projects: { router_template: '/users/:username/projects', uri_template: '/users/{username}/projects' },
  project: { router_template: '/projects/:projectName', uri_template: '/projects/{projectName}' },
  project_update: { router_template: '/projects/:projectName/update', uri_template: '/projects/{projectName}/update' },
  project_create: { router_template: '/users/:username/projects/create', uri_template: '/users/{username}/projects/create' },
  project_label_create: { router_template: '/projects/:projectName/labels/create', uri_template: '/projects/{projectName}/labels/create' },
  project_state_create: { router_template: '/projects/:projectName/states/create', uri_template: '/projects/{projectName}/states/create' },
  project_state_transition_create: { router_template: '/projects/:projectName/state-transitions/create', uri_template: '/projects/{projectName}/state-transitions/create' },
  issue: { router_template: '/issues/:issueId', uri_template: '/issues/{issueId}' },
  issue_create: { router_template: '/projects/:projectName/issues/create', uri_template: '/projects/{projectName}/issues/create' },
  issue_update: { router_template: '/issues/:issueId/update', uri_template: '/issues/{issueId}/update' }
}
