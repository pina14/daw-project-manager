import React from 'react'
import Request from '../../utils/cancelable-request'

export default class extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      issueCreator: this.props.username,
      projectName: this.props.projectName,
      issueName: '',
      description: ''
    }

    this.onChangeIssueNameHandler = this.onChangeIssueNameHandler.bind(this)
    this.onChangeDescriptionHandler = this.onChangeDescriptionHandler.bind(this)
    this.onSubmitHandler = this.onSubmitHandler.bind(this)
  }

  render () {
    return (
      <>
        <h1>Create Issue</h1>
        <form onSubmit={this.onSubmitHandler}>
          <div>
            <label>Name: </label>
            <input type='text' value={this.state.issueName} onChange={this.onChangeIssueNameHandler} required />
          </div>
          <div>
            <label>Description: </label>
            <input type='text' value={this.state.description} onChange={this.onChangeDescriptionHandler} required />
          </div>
          <button>Submit</button>
        </form>
      </>
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
