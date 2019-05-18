import React from 'react'
import HttpRequest from '../general-components/http-request'
import Actions from '../../utils/siren-actions'

export default class extends React.Component {
  constructor (props) {
    super(props)
    this.state = {}
    this.onChangeFullnameHandler = this.onChangeFullnameHandler.bind(this)
    this.onChangeEmailHandler = this.onChangeEmailHandler.bind(this)
  }

  render () {
    return (
      <HttpRequest
        host={this.props.host}
        path={this.props.path}
        method={this.props.method}
        credentials={this.props.credentials}
        onLoaded={(user) => {
          const updateAction = Actions.findByName(user, Actions.update_user)
          return <>
            <h1>Update User</h1>
            <form onSubmit={(ev) => this.update(ev, user)}>
              <div>
                <label>Name: </label>
                <input type={updateAction.fields.find(field => field.name === 'fullName').type}
                  value={this.state.fullName !== undefined ? this.state.fullName : user.properties.fullName} onChange={this.onChangeFullnameHandler} required />
              </div>
              <div>
                <label>Email: </label>
                <input type={updateAction.fields.find(field => field.name === 'email').type}
                  value={this.state.email !== undefined ? this.state.email : user.properties.email} onChange={this.onChangeEmailHandler} required />
              </div>
              <button>Submit</button>
            </form>
          </>
        }}
      />
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

  update (ev, user) {
    ev.preventDefault()
    const body = {}
    Object.assign(body, this.state)
    if (!body.fullName) body.fullName = user.properties.fullName
    if (!body.email) body.email = user.properties.email
    this.request = Actions.call(
      user,
      Actions.update_user,
      this.props.host,
      body,
      this.props.credentials,
      this.props.onSuccess,
      (error) => console.log(error)
    )
  }

  componentWillUnmount () {
    if (this.request) this.request.cancel()
  }
}
