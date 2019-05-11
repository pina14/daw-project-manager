import React from 'react'

export default class extends React.Component {
  constructor (props) {
    super(props)

    this.state = {
      username: '',
      password: ''
    }

    this.onChangeUsernameHandler = this.onChangeUsernameHandler.bind(this)
    this.onChangePasswordHandler = this.onChangePasswordHandler.bind(this)
    this.onClickHandler = this.onClickHandler.bind(this)
  }

  render () {
    return (
      <>
        <h2>Login</h2>
        <form>
          <label>Username</label>
          <input type='text' value={this.state.username} onChange={this.onChangeUsernameHandler} />
          <label>Password</label>
          <input type='password' value={this.state.password} onChange={this.onChangePasswordHandler} />
          <input type='submit' onClick={this.onClickHandler} />
        </form>
      </>
    )
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

  // TODO validate data
  onClickHandler (ev) {
    ev.preventDefault()
    this.props.onSuccess(this.state.username, this.state.password)
  }
}
