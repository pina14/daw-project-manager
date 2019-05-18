import React from 'react'
import Request from '../../utils/cancelable-request'

export default class extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      projectName: this.props.projectName,
      labelName: ''
    }

    this.onChangeLabelNameHandler = this.onChangeLabelNameHandler.bind(this)
    this.onSubmitHandler = this.onSubmitHandler.bind(this)
  }

  render () {
    return (
      <>
        <h1>Create Project Label</h1>
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
      (error) => console.log(error)
    )

    this.request.setHeaders({ 'Authorization': `Basic ${this.props.credentials}` })
    this.request.setBody(this.state)
    this.request.send()
  }

  componentWillUnmount () {
    if (this.request) this.request.cancel()
  }
}
