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
  project_update: { router_template: '/projects/:projectName/update', uri_template: '/projects/{projectName}/update' }
}
