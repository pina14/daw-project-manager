import React from 'react'
import Request from '../../utils/cancelable-request'

export default class extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      fullName: '',
      email: '',
      username: '',
      password: ''
    }

    this.onChangeFullnameHandler = this.onChangeFullnameHandler.bind(this)
    this.onChangeEmailHandler = this.onChangeEmailHandler.bind(this)
    this.onChangeUsernameHandler = this.onChangeUsernameHandler.bind(this)
    this.onChangePasswordHandler = this.onChangePasswordHandler.bind(this)
    this.onSubmitHandler = this.onSubmitHandler.bind(this)
  }

  render () {
    return (
      <>
        <h1>Register</h1>
        <form onSubmit={this.onSubmitHandler}>
          <div>
            <label>Name: </label>
            <input type='text' value={this.state.fullName} onChange={this.onChangeFullnameHandler} required />
          </div>
          <div>
            <label>Email: </label>
            <input type='email' value={this.state.email} onChange={this.onChangeEmailHandler} required />
          </div>
          <div>
            <label>Username: </label>
            <input type='text' value={this.state.username} onChange={this.onChangeUsernameHandler} required />
          </div>
          <div>
            <label>Password: </label>
            <input type='password' value={this.state.password} onChange={this.onChangePasswordHandler} required />
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

  onChangeUsernameHandler (ev) {
    this.setState({
      username: ev.target.value
    })
  }

  onChangePasswordHandler (ev) {
    this.setState({
      password: ev.target.value
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

    this.request.setBody(this.state)
    this.request.send()
  }

  componentWillUnmount () {
    if (this.request) this.request.cancel()
  }
}
