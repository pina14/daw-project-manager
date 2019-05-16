import parser from 'uri-template'

export default class UriBuilder {
  static build (template, templateVariables) {
    const parsedUri = parser.parse(template)
    return parsedUri.expand(templateVariables)
  }
}
