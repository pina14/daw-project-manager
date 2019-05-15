import React from 'react'
import Request from '../utils/cancelable-request'

export default class extends React.Component {
  constructor (props) {
    super(props)

    this.state = {
      username: '',
      password: '',
      wrongAuth: false
    }

    this.request = undefined
    this.onChangeUsernameHandler = this.onChangeUsernameHandler.bind(this)
    this.onChangePasswordHandler = this.onChangePasswordHandler.bind(this)
    this.onSubmitHandler = this.onSubmitHandler.bind(this)
  }

  render () {
    return (
      <>
        <h2>Login</h2>
        <div style={{ color: 'red', display: this.state.wrongAuth ? 'block' : 'none' }}>
          Wrong Credentials
        </div>
        <form onSubmit={(ev) => this.onSubmitHandler(ev)}>
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

  async onSubmitHandler (ev) {
    ev.preventDefault()
    const credentials = Buffer.from(`${this.state.username}:${this.state.password}`).toString('base64')
    this.request = new Request(
      this.props.host,
      this.props.path,
      undefined,
      this.props.method,
      () => {
        this.setState({ wrongAuth: false })
        this.props.onSuccess(this.state.username, this.state.password, credentials)
      },
      () => this.setState({ wrongAuth: true })
    )

    this.request.setHeaders({ 'Authorization': `Basic ${credentials}` })
    this.request.send()
  }

  success (credentials) {
    this.setState({ wrongAuth: false })
    this.props.onSuccess(this.state.username, this.state.password, credentials)
  }

  componentWillUnmount () {
    if (this.request) this.request.cancel()
  }
}
