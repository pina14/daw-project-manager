export default class SubEntities {
  static findByName (obj, relName) {
    if (!obj.entities) return undefined
    return obj.entities.find((elem) => {
      if (!elem.rel) return false
      return elem.rel.some((r) => r === relName)
    })
  }
}
