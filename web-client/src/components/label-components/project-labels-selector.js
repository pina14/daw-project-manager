import React from 'react'
import HttpRequest from '../general-components/http-request'
import SubEntities from '../../utils/siren-sub-entities'
import EntitiesProperties from '../../utils/siren-entities-properties'
import Rels from '../../utils/rels'

export default class extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      labelName: '-'
    }

    this.handleChange = this.handleChange.bind(this)
  }

  render () {
    return (
      <>
        <HttpRequest
          host={this.props.host}
          path={SubEntities.findByName(this.props.project, Rels.project_labels).href}
          method='GET'
          credentials={this.props.credentials}
          onLoaded={(labels) => {
            return (
              <select value={this.state.labelName} onChange={this.handleChange} >
                {this.renderOptions(labels)}
              </select>
            )
          }} />
      </>
    )
  }

  renderOptions (labels) {
    const properties = EntitiesProperties.getProperties(labels, Rels.project_label)
    const selectedName = this.state.labelName
    return <>
      <option value={selectedName} key={selectedName}>{selectedName}</option>
      {properties
        .filter(s => s.labelName !== selectedName)
        .map(s => <option value={s.labelName} key={s.labelName}>{s.labelName}</option>)}
    </>
  }

  handleChange (ev) {
    this.setState(
      { labelName: ev.target.value },
      () => this.props.onChangeState(this.state.labelName)
    )
  }
}
