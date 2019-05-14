import React from 'react'

export default class extends React.Component {
  constructor (props) {
    super(props)

    if (this.props.user) {
      this.state = {
        fullname: this.props.user.fullname,
        email: this.props.user.email,
        username: this.props.user.username
      }
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
        <form onSubmit={this.onSubmitHandler}>
          <div>
            <label>Name: </label>
            <input type='text' value={this.state.fullname} onChange={this.onChangeFullnameHandler} required />
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

  }

  onChangeEmailHandler (ev) {

  }

  onChangeUsernameHandler (ev) {

  }

  onChangePasswordHandler (ev) {

  }

  onSubmitHandler (ev) {
    ev.preventDefault()
    this.props.submit()
  }
}
