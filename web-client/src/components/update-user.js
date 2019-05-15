import React from 'react'
import Request from '../utils/cancelable-request'
import Actions from '../utils/actions'

export default class extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      fullName: this.props.user.properties.fullName,
      email: this.props.user.properties.email
    }
    this.updateAction = Actions.findByName(this.props.user, 'update-user')
    this.update = this.update.bind(this)
    this.onChangeFullnameHandler = this.onChangeFullnameHandler.bind(this)
    this.onChangeEmailHandler = this.onChangeEmailHandler.bind(this)
  }

  render () {
    return (
      <>
        <h1>Update User</h1>
        <form onSubmit={this.update}>
          <div>
            <label>Name: </label>
            <input type={this.updateAction.fields.find(field => field.name === 'fullName')}
              value={this.state.fullName} onChange={this.onChangeFullnameHandler} required />
          </div>
          <div>
            <label>Email: </label>
            <input type={this.updateAction.fields.find(field => field.name === 'email')}
              value={this.state.email} onChange={this.onChangeEmailHandler} required />
          </div>
          <button>Submit</button>
        </form>
      </>
    )
  }

  onChangeFullnameHandler (ev) {
    this.setState({
      fullName: ev.target.value
    })
  }

  onChangeEmailHandler (ev) {
    this.setState({
      email: ev.target.value
    })
  }

  update (ev) {
    ev.preventDefault()
    this.request = new Request(
      this.props.host,
      this.updateAction.href,
      undefined,
      this.updateAction.method,
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
