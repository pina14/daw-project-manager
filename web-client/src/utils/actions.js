module.exports = {
  findByName
}

function findByName (obj, actionName) {
  if (!obj.actions) return undefined
  const action = obj.actions.find((elem) => elem.name === actionName)
  return action
}
