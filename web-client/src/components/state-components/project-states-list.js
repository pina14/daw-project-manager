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
      version: 0,
      notAllowedToDeleteMessage: null
    }
  }

  render () {
    return (
      <>
        <div style={{ color: 'red', display: this.state.notAllowedToDeleteMessage ? 'block' : 'none' }}>
          {this.state.notAllowedToDeleteMessage}
        </div>
        <HttpRequest
          host={this.props.host}
          path={this.props.path}
          method={this.props.method}
          version={this.state.version}
          credentials={this.props.credentials}
          onLoaded={(states) => {
            const statesProperties = EntitiesProperties.getProperties(states, Rels.project_state)
            return (
              <>
                <table>
                  <thead key='tableHead'>
                    <tr key='headerkey'>
                      <th key='Name'>Name</th>
                    </tr>
                  </thead>
                  <tbody key='tableBody'>
                    {statesProperties.map((properties) => {
                      return (
                        <tr key={properties.stateName}>
                          <td key={`stateName=${properties.stateName}`}>
                            {properties.stateName}
                          </td>
                          <td key={`delete=${properties.stateName}`}>
                            <button onClick={() => this.deleteState(properties.stateName)} >
                              Delete
                            </button>
                          </td>
                        </tr>
                      )
                    })}
                  </tbody>
                </table>
                <Link to={ClientPaths.projectStateCreateTemplateFilled(this.props.projectName)} >
                  <button>Add Project State</button>
                </Link>
              </>
            )
          }}
        />
      </>
    )
  }

  deleteState (stateName) {
    this.request = new Request(
      this.props.host,
      ApiPaths.projectStatesUrl(this.props.projectName, stateName),
      'DELETE',
      () => this.setState((prevState) => ({
        version: prevState.version + 1,
        notAllowedToDeleteMessage: null
      })),
      () => this.setState({
        notAllowedToDeleteMessage: `Can't delete state ${stateName}`
      })
    )

    this.request.setHeaders({ 'Authorization': `Basic ${this.props.credentials}` })
    this.request.send()
  }

  componentWillUnmount () {
    if (this.request) this.request.cancel()
  }
}
