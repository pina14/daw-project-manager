import React from 'react'
import Request from '../../utils/cancelable-request'

export default class extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      name: '',
      description: '',
      username: this.props.username,
      defaultStateName: '',
      invalidProjectMessage: null
    }

    this.onChangeNameHandler = this.onChangeNameHandler.bind(this)
    this.onChangeDescriptionHandler = this.onChangeDescriptionHandler.bind(this)
    this.onChangeDefaultStateHandler = this.onChangeDefaultStateHandler.bind(this)
    this.onSubmitHandler = this.onSubmitHandler.bind(this)
  }

  render () {
    return (
      <>
        <h1>Create Project</h1>
        <div style={{ color: 'red', display: this.state.invalidProjectMessage ? 'block' : 'none' }}>
          {this.state.invalidProjectMessage}
        </div>
        <form onSubmit={this.onSubmitHandler}>
          <div>
            <label>Name: </label>
            <input type='text' value={this.state.name} onChange={this.onChangeNameHandler} required />
          </div>
          <div>
            <label>Description: </label>
            <input type='text' value={this.state.description} onChange={this.onChangeDescriptionHandler} required />
          </div>
          <div>
            <label>Default State for Issues: </label>
            <input type='text' value={this.state.defaultStateName} onChange={this.onChangeDefaultStateHandler} required />
          </div>
          <button>Submit</button>
        </form>
      </>
    )
  }

  onChangeNameHandler (ev) {
    this.setState({
      name: ev.target.value
    })
  }

  onChangeDescriptionHandler (ev) {
    this.setState({
      description: ev.target.value
    })
  }

  onChangeDefaultStateHandler (ev) {
    this.setState({
      defaultStateName: ev.target.value
    })
  }

  onSubmitHandler (ev) {
    ev.preventDefault()

    this.request = new Request(
      this.props.host,
      this.props.path,
      this.props.method,
      this.props.onSuccess,
      () => this.setState({ invalidProjectMessage: 'Project Name has to be unique.' })
    )

    this.request.setHeaders({ 'Authorization': `Basic ${this.props.credentials}` })
    this.request.setBody(this.state)
    this.request.send()
  }

  componentWillUnmount () {
    if (this.request) this.request.cancel()
  }
}
