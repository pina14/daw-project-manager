import React from 'react'
import Request from '../../utils/cancelable-request'
import HttpRequest from '../general-components/http-request'
import ApiPaths from '../../utils/api-paths'
import ProjectLabelsSelector from './project-labels-selector'

export default class extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      projectName: this.props.projectName,
      issueId: this.props.issueId,
      labelName: '',
      invalidLabelMessage: null
    }

    this.onChangeLabelNameHandler = this.onChangeLabelNameHandler.bind(this)
    this.onSubmitHandler = this.onSubmitHandler.bind(this)
  }

  render () {
    return (
      <>
        <h1>Add Issue Label</h1>
        <div style={{ color: 'red', display: this.state.invalidLabelMessage ? 'block' : 'none' }}>
          {this.state.invalidLabelMessage}
        </div>
        <HttpRequest
          host={this.props.host}
          path={ApiPaths.projectUrl(this.props.projectName)}
          method='GET'
          credentials={this.props.credentials}
          onLoaded={(project) => {
            return (
              <form onSubmit={this.onSubmitHandler}>
                <div>
                  <label>Name: </label>
                  <ProjectLabelsSelector
                    project={project}
                    host={this.props.host}
                    credentials={this.props.credentials}
                    onChangeState={this.onChangeLabelNameHandler} />
                </div>
                <button>Submit</button>
              </form>
            )
          }} />
      </>
    )
  }

  onChangeLabelNameHandler (label) {
    this.setState({
      labelName: label
    })
  }

  onSubmitHandler (ev) {
    ev.preventDefault()

    this.request = new Request(
      this.props.host,
      this.props.path,
      this.props.method,
      this.props.onSuccess,
      () => this.setState({ invalidLabelMessage: 
        this.state.labelName
          ? `Issue already has '${this.state.labelName}' label`
          : 'Select a label to add.'
      })
    )

    this.request.setHeaders({ 'Authorization': `Basic ${this.props.credentials}` })
    this.request.setBody(this.state)
    this.request.send()
  }

  componentWillUnmount () {
    if (this.request) this.request.cancel()
  }
}
