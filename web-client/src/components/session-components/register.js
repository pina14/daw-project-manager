import React from 'react'
import UserForm from '../user-components/user-form'
import Request from '../../utils/cancelable-request'

export default class extends React.Component {
  constructor (props) {
    super(props)
    this.register = this.register.bind(this)
  }

  render () {
    return (
      <>
        <h1>Register</h1>
        <UserForm submit={this.register} />
      </>
    )
  }

  componentWillUnmount () {
    if (this.request) this.request.cancel()
  }

  register (state) {
    this.request = new Request(
      this.props.host,
      this.props.path,
      this.props.method,
      () => {
        this.props.onSuccess()
      },
      (error) => console.log(error)
    )

    this.request.setBody(state)
    this.request.send()
  }
}
