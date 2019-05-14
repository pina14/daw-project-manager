import React from 'react'
import HttpRequest from './http-request'
import Request from '../utils/cancelable-request'
import Actions from '../utils/actions'

export default class extends React.Component {
  render () {
    return (
      <HttpRequest
        host={this.props.host}
        path={this.props.path.replace('{username}', this.props.username)}
        method={this.props.method}
        credentials={this.props.credentials}
        onLoaded={(user) => <>
          <div>
            <b>Full Name: </b>
            <p>{user.properties.fullName}</p>
          </div>
          <div>
            <b>Username: </b>
            <p>{user.properties.username}</p>
          </div>
          <div>
            <b>Email: </b>
            <p>{user.properties.email}</p>
          </div>
          <div>
            <button onClick={() => this.DeleteUser(user)}> Delete User</button>
            <button onClick={() => this.UpdateUser(user)}>Update User</button>
          </div>
        </>} />
    )
  }

  componentWillMount () {
    if (this.deleteRequest) this.deleteRequest.cancel()
    if (this.updateRequest) this.updateRequest.cancel()
  }

  DeleteUser (user) {
    const action = Actions.findByName(user, 'delete-user')
    this.deleteRequest = new Request(
      this.props.host,
      action.href,
      action.method,
      () => this.props.onDelete(),
      (error) => console.log(error)
    )

    console.log(this.deleteRequest)
    this.deleteRequest.setHeaders({ 'Authorization': `Basic ${this.props.credentials}` })
    this.deleteRequest.send()
  }

  UpdateUser (user) {

  }
}
