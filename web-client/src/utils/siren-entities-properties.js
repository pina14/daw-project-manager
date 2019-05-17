export default class EntitiesProperties {
  static getProperties (entity, relName) {
    if (!entity) return []

    return entity.entities.map((elem) => {
      if (elem.rel.some((r) => r === relName)) {
        return elem.properties
      }
    })
  }
}
