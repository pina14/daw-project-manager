import React from 'react'
import HttpRequest from '../http-request'
import SubEntities from '../../utils/siren-sub-entities'
import EntitiesProperties from '../../utils/siren-entities-properties'

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
          path={SubEntities.findByName(this.props.project, '/rels/project-available-states').href}
          method='GET'
          credentials={this.props.credentials}
          onLoaded={(states) => {
            return (
              <select value={this.state.stateName} onChange={this.handleChange} >
                {this.renderOptions(states)}
              </select>
            )
          }} />
      </>
    )
  }

  renderOptions (states) {
    const properties = EntitiesProperties.getProperties(states, '/rels/project-available-state')
    const selected = properties.find(s => s.stateName === this.state.stateName)
    if (selected) {
      const selectedName = selected.stateName
      return <>
        <option value={selectedName} key={selectedName}>{selectedName}</option>
        {properties
          .filter(s => s.stateName !== selectedName)
          .map(s => <option value={s.stateName} key={s.stateName}>{s.stateName}</option>)}
      </>
    }

    return properties.map(s => <option value={s.stateName} key={s.stateName}>{s.stateName}</option>)
  }

  handleChange (ev) {
    this.setState(
      { stateName: ev.target.value },
      () => this.props.onChangeState(this.state.stateName)
    )
  }
}
