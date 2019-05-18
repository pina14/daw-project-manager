import React from 'react'
import HttpRequest from '../general-components/http-request'
import Actions from '../../utils/siren-actions'
import ProjectStatesSelector from '../state-components/project-states-selector'

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
          const updateAction = Actions.findByName(project, Actions.update_project)
          return <>
            <h1>{`Update ${project.properties.name}`}</h1>
            <form onSubmit={(ev) => this.update(ev, project)}>
              <div>
                <label>Description: </label>
                <input type={updateAction.fields.find(field => field.name === 'description').type}
                  value={this.state.description !== undefined ? this.state.description : project.properties.description}
                  onChange={this.onChangeDescriptionHandler} required />
              </div>
              <div>
                <label>Default Issue State: </label>
                <ProjectStatesSelector
                  project={project}
                  host={this.props.host}
                  credentials={this.props.credentials}
                  initialState={project.properties.defaultStateName}
                  onChangeState={this.onChangeDefaultStateNameHandler} />
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

  onChangeDefaultStateNameHandler (state) {
    this.setState({
      defaultStateName: state
    })
  }

  update (ev, project) {
    ev.preventDefault()
    const body = {}
    Object.assign(body, this.state)
    if (!body.description) body.description = project.properties.description
    if (!body.defaultStateName) body.defaultStateName = project.properties.defaultStateName
    this.request = Actions.call(
      project,
      Actions.update_project,
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
