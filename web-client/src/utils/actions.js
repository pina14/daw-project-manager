import Request from '../utils/cancelable-request'

export function findByName (obj, actionName) {
  if (!obj.actions) return undefined
  return obj.actions.find((elem) => elem.name === actionName)
}

export function call (entity, actionName, host, body, credentials, onSuccess, onError) {
  if (!entity) return undefined
  const action = findByName(entity, actionName)
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
