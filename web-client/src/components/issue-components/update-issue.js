import React from 'react'
import HttpRequest from '../general-components/http-request'
import Actions from '../../utils/siren-actions'
import ProjectStateTransitionsSelector from '../state-components/project-state-transitions-selector'

export default class extends React.Component {
  constructor (props) {
    super(props)
    this.state = {}
    this.onChangeIssueNameHandler = this.onChangeIssueNameHandler.bind(this)
    this.onChangeDescriptionHandler = this.onChangeDescriptionHandler.bind(this)
    this.onChangeStateNameHandler = this.onChangeStateNameHandler.bind(this)
  }

  render () {
    return (
      <HttpRequest
        host={this.props.host}
        path={this.props.path}
        method={this.props.method}
        credentials={this.props.credentials}
        onLoaded={(issue) => {
          const updateAction = Actions.findByName(issue, Actions.update_issue)
          return <>
            <h1>{`Update ${issue.properties.issueName}`}</h1>
            <form onSubmit={(ev) => this.update(ev, issue)}>
              <div>
                <label>Name: </label>
                <input type={updateAction.fields.find(field => field.name === 'issueName').type}
                  value={this.state.issueName !== undefined ? this.state.issueName : issue.properties.issueName}
                  onChange={this.onChangeIssueNameHandler} required />
              </div>
              <div>
                <label>Description: </label>
                <input type={updateAction.fields.find(field => field.name === 'description').type}
                  value={this.state.description !== undefined ? this.state.description : issue.properties.description}
                  onChange={this.onChangeDescriptionHandler} required />
              </div>
              <div>
                <label>State: </label>
                <ProjectStateTransitionsSelector
                  projectName={issue.properties.projectName}
                  host={this.props.host}
                  credentials={this.props.credentials}
                  initialState={issue.properties.state}
                  onChangeState={this.onChangeStateNameHandler} />
              </div>
              <button>Submit</button>
            </form>
          </>
        }}
      />
    )
  }

  onChangeIssueNameHandler (ev) {
    this.setState({
      issueName: ev.target.value
    })
  }

  onChangeDescriptionHandler (ev) {
    this.setState({
      description: ev.target.value
    })
  }

  onChangeStateNameHandler (state) {
    this.setState({
      state: state
    })
  }

  update (ev, issue) {
    ev.preventDefault()
    const body = {}
    Object.assign(body, this.state)
    if (!body.issueName) body.issueName = issue.properties.issueName
    if (!body.description) body.description = issue.properties.description
    if (!body.state) body.state = issue.properties.state
    this.request = Actions.call(
      issue,
      Actions.update_issue,
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
