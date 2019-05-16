import React from 'react'
import HttpRequest from '../http-request'
import Actions from '../../utils/siren-actions'

export default class extends React.Component {
  render () {
    return (
      <HttpRequest
        host={this.props.host}
        path={this.props.path}
        method={this.props.method}
        credentials={this.props.credentials}
        onLoaded={(user) => {
          return <>
            <h1>{this.props.projectName}</h1>
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
              <button onClick={() => this.Delete(user)}> Delete User</button>
              <button onClick={this.props.onUpdate}>Update User</button>
            </div>
          </>
        }}
      />
    )
  }

  componentWillMount () {
    if (this.request) this.request.cancel()
  }

  Delete (user) {
    this.request = Actions.call(
      user,
      'delete-user',
      this.props.host,
      undefined,
      this.props.credentials,
      this.props.onDelete,
      (error) => console.log(error)
    )
  }
}
