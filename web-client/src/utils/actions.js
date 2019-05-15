module.exports = {
  findByName
}

function findByName (obj, actionName) {
  if (!obj.actions) return undefined
  return obj.actions.find((elem) => elem.name === actionName)
}
