import React from 'react'
import HttpRequest from '../general-components/http-request'
import { Link } from 'react-router-dom'
import ClientPaths from '../../utils/client-paths'
import ApiPaths from '../../utils/api-paths'
import Request from '../../utils/cancelable-request'
import EntitiesProperties from '../../utils/siren-entities-properties'
import Rels from '../../utils/rels'

export default class extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      version: 0
    }
  }

  render () {
    return (
      <>
        <HttpRequest
          host={this.props.host}
          path={this.props.path}
          method={this.props.method}
          version={this.state.version}
          credentials={this.props.credentials}
          onLoaded={(labels) => {
            const labelsProperties = EntitiesProperties.getProperties(labels, Rels.project_label)
            return (
              <>
                <table>
                  <thead key='tableHead'>
                    <tr key='headerkey'>
                      <th key='Name'>Name</th>
                    </tr>
                  </thead>
                  <tbody key='tableBody'>
                    {labelsProperties.map((properties) => {
                      return (
                        <tr key={properties.labelName}>
                          <td key={`labelName=${properties.labelName}`}>
                            {properties.labelName}
                          </td>
                          <td key={`delete=${properties.labelName}`}>
                            <button onClick={() => this.deleteLabel(properties.labelName)} >
                              Delete
                            </button>
                          </td>
                        </tr>
                      )
                    })}
                  </tbody>
                </table>
                <Link to={ClientPaths.projectLabelCreateTemplateFilled(this.props.projectName)} >
                  <button>Add Project Label</button>
                </Link>
              </>
            )
          }}
        />
      </>
    )
  }

  deleteLabel (labelName) {
    this.request = new Request(
      this.props.host,
      ApiPaths.projectLabelsUrl(this.props.projectName, labelName),
      'DELETE',
      () => this.setState((prevState) => ({ version: prevState.version + 1 })),
      (error) => console.log(error)
    )

    this.request.setHeaders({ 'Authorization': `Basic ${this.props.credentials}` })
    this.request.send()
  }

  componentWillUnmount () {
    if (this.request) this.request.cancel()
  }
}
