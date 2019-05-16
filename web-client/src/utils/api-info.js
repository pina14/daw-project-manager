
export default class Paths {
  static getHost () { return 'http://localhost:8090' }
  static getPaths () { return Paths.paths }
  static setPaths (paths) { Paths.paths = paths }
}

Paths.paths = {}
