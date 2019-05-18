import React from 'react'
import Request from '../../utils/cancelable-request'
import ProjectStatesSelector from '../state-components/project-states-selector'
import HttpRequest from '../general-components/http-request'
import ApiPaths from '../../utils/api-paths'

export default class extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      projectName: this.props.projectName,
      fromState: '',
      toState: '',
      invalidTransitionMessage: null
    }

    this.onChangeFromStateNameHandler = this.onChangeFromStateNameHandler.bind(this)
    this.onChangeToStateNameHandler = this.onChangeToStateNameHandler.bind(this)
    this.onSubmitHandler = this.onSubmitHandler.bind(this)
  }

  render () {
    return (
      <>
        <h1>Create Project State Transition</h1>
        <div style={{ color: 'red', display: this.state.invalidTransitionMessage ? 'block' : 'none' }}>
          {this.state.invalidTransitionMessage}
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
                  <label>From State: </label>
                  <ProjectStatesSelector
                    project={project}
                    host={this.props.host}
                    credentials={this.props.credentials}
                    initialState={'-'}
                    onChangeState={this.onChangeFromStateNameHandler} />
                </div>
                <div>
                  <label>To State: </label>
                  <ProjectStatesSelector
                    project={project}
                    host={this.props.host}
                    credentials={this.props.credentials}
                    initialState={'-'}
                    onChangeState={this.onChangeToStateNameHandler} />
                </div>
                <button>Submit</button>
              </form>
            )
          }} />
      </>
    )
  }

  onChangeFromStateNameHandler (state) {
    this.setState({
      fromState: state
    })
  }

  onChangeToStateNameHandler (state) {
    this.setState({
      toState: state
    })
  }

  onSubmitHandler (ev) {
    ev.preventDefault()

    this.request = new Request(
      this.props.host,
      this.props.path,
      this.props.method,
      this.props.onSuccess,
      () => this.setState({ invalidTransition: 'Invalid or Repeated Transition.' })
    )

    this.request.setHeaders({ 'Authorization': `Basic ${this.props.credentials}` })
    this.request.setBody(this.state)
    this.request.send()
  }

  componentWillUnmount () {
    if (this.request) this.request.cancel()
  }
}
