import Request from '../utils/cancelable-request'

export default class SubEntities {
  static findByName (obj, relName) {
    if (!obj.entities) return undefined
    return obj.entities.find((elem) => {
      if (!elem.rel) return false
      return elem.rel.some((r) => r === relName)
    })
  }

  static call (entity, relName, host, credentials, onSuccess, onError) {
    if (!entity) return undefined
    const rel = SubEntities.findByName(entity, relName)
    if (!rel) return undefined
    const request = new Request(
      host,
      rel.href,
      'GET',
      (json) => onSuccess(json),
      onError)

    request.setHeaders({ 'Authorization': `Basic ${credentials}` })
    request.send()
    return request
  }
}
