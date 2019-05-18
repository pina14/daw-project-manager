import React from 'react'
import Request from '../../utils/cancelable-request'

export default class extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      commentCreator: this.props.username,
      issueId: this.props.issueId,
      content: '',
      invalidMessage: null
    }

    this.onChangeContentHandler = this.onChangeContentHandler.bind(this)
    this.onSubmitHandler = this.onSubmitHandler.bind(this)
  }

  render () {
    return (
      <>
        <h1>Create Issue Comment</h1>
        <div style={{ color: 'red', display: this.state.invalidMessage ? 'block' : 'none' }}>
          {this.state.invalidMessage}
        </div>
        <form onSubmit={this.onSubmitHandler}>
          <div>
            <label>Content: </label>
            <input type='text' value={this.state.content} onChange={this.onChangeContentHandler} required />
          </div>
          <button>Submit</button>
        </form>
      </>
    )
  }

  onChangeContentHandler (ev) {
    this.setState({
      content: ev.target.value
    })
  }

  onSubmitHandler (ev) {
    ev.preventDefault()

    this.request = new Request(
      this.props.host,
      this.props.path,
      this.props.method,
      this.props.onSuccess,
      () => this.setState({ invalidMessage: 'Error creating comment. Check if issue is not in \'archived state.' })
    )

    this.request.setHeaders({ 'Authorization': `Basic ${this.props.credentials}` })
    this.request.setBody(this.state)
    this.request.send()
  }

  componentWillUnmount () {
    if (this.request) this.request.cancel()
  }
}
