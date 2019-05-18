import React from 'react'
import Request from '../../utils/cancelable-request'

export default class extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      projectName: this.props.projectName,
      labelName: '',
      invalidLabelMessage: null
    }

    this.onChangeLabelNameHandler = this.onChangeLabelNameHandler.bind(this)
    this.onSubmitHandler = this.onSubmitHandler.bind(this)
  }

  render () {
    return (
      <>
        <h1>Create Project Label</h1>
        <div style={{ color: 'red', display: this.state.invalidLabelMessage ? 'block' : 'none' }}>
          {this.state.invalidLabelMessage}
        </div>
        <form onSubmit={this.onSubmitHandler}>
          <div>
            <label>Name: </label>
            <input type='text' value={this.state.labelName} onChange={this.onChangeLabelNameHandler} required />
          </div>
          <button>Submit</button>
        </form>
      </>
    )
  }

  onChangeLabelNameHandler (ev) {
    this.setState({
      labelName: ev.target.value
    })
  }

  onSubmitHandler (ev) {
    ev.preventDefault()

    this.request = new Request(
      this.props.host,
      this.props.path,
      this.props.method,
      this.props.onSuccess,
      () => this.setState({ invalidLabelMessage: 'Label Name has to be unique within the project.' })
    )

    this.request.setHeaders({ 'Authorization': `Basic ${this.props.credentials}` })
    this.request.setBody(this.state)
    this.request.send()
  }

  componentWillUnmount () {
    if (this.request) this.request.cancel()
  }
}
