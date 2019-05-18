import React from 'react'
import HttpRequest from '../general-components/http-request'
import EntitiesProperties from '../../utils/siren-entities-properties'
import Rels from '../../utils/rels'
import ApiPaths from '../../utils/api-paths'

export default class extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      stateName: this.props.initialState
    }

    this.handleChange = this.handleChange.bind(this)
  }

  render () {
    return (
      <>
        <HttpRequest
          host={this.props.host}
          path={ApiPaths.projectStateTransitionsUrl(this.props.projectName)}
          method='GET'
          credentials={this.props.credentials}
          onLoaded={(transitions) => {
            return (
              <select value={this.state.stateName} onChange={this.handleChange} >
                {this.renderOptions(transitions)}
              </select>
            )
          }} />
      </>
    )
  }

  renderOptions (transitions) {
    const properties = EntitiesProperties.getProperties(transitions, Rels.project_state_transition)
    const selectedName = this.state.stateName
    return <>
      <option value={selectedName} key={selectedName}>{selectedName}</option>
      {properties
        .filter(t => t.toState !== selectedName && t.fromState === this.props.initialState)
        .map(s => <option value={s.toState} key={s.toState}>{s.toState}</option>)}
    </>
  }

  handleChange (ev) {
    this.setState(
      { stateName: ev.target.value },
      () => this.props.onChangeState(this.state.stateName)
    )
  }
}
