import React from 'react'
import HttpRequest from '../http-request'
import { findByName, call } from '../../utils/actions'

export default class extends React.Component {
  constructor (props) {
    super(props)
    this.state = {}
    this.onChangeDescriptionHandler = this.onChangeDescriptionHandler.bind(this)
    this.onChangeDefaultStateNameHandler = this.onChangeDefaultStateNameHandler.bind(this)
  }

  render () {
    return (
      <HttpRequest
        host={this.props.host}
        path={this.props.path}
        method={this.props.method}
        credentials={this.props.credentials}
        onLoaded={(project) => {
          const updateAction = findByName(project, 'update-project')
          return <>
            <h1>{`Update ${project.properties.name}`}</h1>
            <form onSubmit={(ev) => this.update(ev, project)}>
              <div>
                <label>Description: </label>
                <input type={updateAction.fields.find(field => field.name === 'description')}
                  value={this.state.description !== undefined ? this.state.description : project.properties.description}
                  onChange={this.onChangeDescriptionHandler} required />
              </div>
              <div>
                <label>Default Issue State: </label>
                <input type={updateAction.fields.find(field => field.name === 'defaultStateName')}
                  value={this.state.defaultStateName !== undefined ? this.state.defaultStateName : project.properties.defaultStateName}
                  onChange={this.onChangeDefaultStateNameHandler} required />
              </div>
              <button>Submit</button>
            </form>
          </>
        }}
      />
    )
  }

  onChangeDescriptionHandler (ev) {
    this.setState({
      description: ev.target.value
    })
  }

  onChangeDefaultStateNameHandler (ev) {
    this.setState({
      defaultStateName: ev.target.value
    })
  }

  update (ev, project) {
    ev.preventDefault()
    const body = {}
    Object.assign(body, this.state)
    if (!body.description) body.description = project.properties.description
    if (!body.defaultStateName) body.defaultStateName = project.properties.defaultStateName
    this.request = call(
      project,
      'update-project',
      this.props.host,
      body,
      this.props.credentials,
      this.props.onSuccess,
      (error) => console.log(error)
    )
  }

  componentWillUnmount () {
    if (this.request) this.request.cancel()
  }
}
