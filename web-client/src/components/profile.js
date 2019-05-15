import React from 'react'
import HttpRequest from './http-request'
import Request from '../utils/cancelable-request'
import Actions from '../utils/actions'
import UpdateUser from './update-user'

export default class extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      showNupdate: true
    }
  }

  render () {
    console.log('render')
    return (
      <HttpRequest
        host={this.props.host}
        path={this.props.path}
        method={this.props.method}
        credentials={this.props.credentials}
        onLoaded={(user) => {
          return this.state.showNupdate
            ? (<>
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
            </>) : (<UpdateUser
              user={user}
              host={this.props.host}
              credentials={this.props.credentials}
              onSuccess={() => this.setState({ showNupdate: true })}
            />)
        }} />
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

    this.deleteRequest.setHeaders({ 'Authorization': `Basic ${this.props.credentials}` })
    this.deleteRequest.send()
  }

  UpdateUser (user) {
    this.setState({ showNupdate: false })
  }
}
