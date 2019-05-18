import React from 'react'
import Request from '../../utils/cancelable-request'

export default class extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      projectName: this.props.projectName,
      stateName: ''
    }

    this.onChangeStateNameHandler = this.onChangeStateNameHandler.bind(this)
    this.onSubmitHandler = this.onSubmitHandler.bind(this)
  }

  render () {
    return (
      <>
        <h1>Create Project State</h1>
        <form onSubmit={this.onSubmitHandler}>
          <div>
            <label>Name: </label>
            <input type='text' value={this.state.stateName} onChange={this.onChangeStateNameHandler} required />
          </div>
          <button>Submit</button>
        </form>
      </>
    )
  }

  onChangeStateNameHandler (ev) {
    this.setState({
      stateName: ev.target.value
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
