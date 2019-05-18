import Request from '../utils/cancelable-request'

export default class Actions {
  static findByName (obj, actionName) {
    if (!obj.actions) return undefined
    return obj.actions.find((elem) => elem.name === actionName)
  }

  static call (entity, actionName, host, body, credentials, onSuccess, onError) {
    if (!entity) return undefined
    const action = Actions.findByName(entity, actionName)
    if (!action) return undefined
    const request = new Request(
      host,
      action.href,
      action.method,
      onSuccess,
      onError)

    request.setHeaders({ 'Authorization': `Basic ${credentials}` })
    if (body) request.setBody(body)
    request.send()
    return request
  }
}

Actions.update_user = 'update-user'
Actions.delete_user = 'delete-user'

Actions.create_project = 'create-project'
Actions.update_project = 'update-project'
Actions.delete_project = 'delete-project'

Actions.add_project_label = 'add-project-available-label'
Actions.add_project_state = 'add-project-available-state'
Actions.add_project_state_transition = 'add-project-state-transition'

Actions.create_issue = 'create-issue'
Actions.update_issue = 'update-issue'
Actions.delete_issue = 'delete-issue'

Actions.add_issue_label = 'add-issue-label'
Actions.create_issue_comment = 'create-issue-comment'
