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
          onLoaded={(statesTransitions) => {
            const statesTransitionsProperties = EntitiesProperties.getProperties(statesTransitions, Rels.project_state_transition)
            return (
              <>
                <table>
                  <thead key='tableHead'>
                    <tr key='headerkey'>
                      <th key='FromState'>From</th>
                      <th key='ToState'>To</th>
                    </tr>
                  </thead>
                  <tbody key='tableBody'>
                    {statesTransitionsProperties.map((properties) => {
                      return (
                        <tr key={properties.fromState + properties.toState}>
                          <td key={`from${properties.fromState}${properties.toState}`}>
                            {properties.fromState}
                          </td>
                          <td key={`to${properties.fromState}${properties.toState}`}>
                            {properties.toState}
                          </td>
                          <td key={`delete${properties.fromState}${properties.toState}`}>
                            <button onClick={() => this.deleteStateTransition(properties.fromState, properties.toState)} >
                              Delete
                            </button>
                          </td>
                        </tr>
                      )
                    })}
                  </tbody>
                </table>
                <Link to={ClientPaths.projectStateTransitionCreateTemplateFilled(this.props.projectName)} >
                  <button>Add Project State Transition</button>
                </Link>
              </>
            )
          }}
        />
      </>
    )
  }

  deleteStateTransition (fromState, toState) {
    this.request = new Request(
      this.props.host,
      ApiPaths.projectStateTransitionsUrl(this.props.projectName, fromState, toState),
      'DELETE',
      () => this.setState((prevState) => ({
        version: prevState.version + 1,
        notAllowedToDeleteMessage: null
      })),
      () => this.setState({
        notAllowedToDeleteMessage: `Can't delete transition ${fromState} -> ${toState}.`
      })
    )

    this.request.setHeaders({ 'Authorization': `Basic ${this.props.credentials}` })
    this.request.send()
  }

  componentWillUnmount () {
    if (this.request) this.request.cancel()
  }
}
