import React from 'react'
import AppRouter from '../general-components/app-router'
import ClientPaths from '../../utils/client-paths'

export default class extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      username: '',
      password: '',
      base64auth: ''
    }

    this.authenticate = this.authenticate.bind(this)
    this.signout = this.signout.bind(this)
    this.isAuthenticated = this.isAuthenticated.bind(this)
  }

  render () {
    return (
      <>
        <AppRouter
          authenticate={this.authenticate}
          signout={this.signout}
          isAuthenticated={this.isAuthenticated}
          {...this.state} />
      </>
    )
  }

  authenticate (history, username, password, base64auth) {
    this.setState({
      username: username,
      password: password,
      base64auth: base64auth
    })
    history.push(ClientPaths.homeTemplateFilled())
  }

  signout (history) {
    this.setState({
      username: '',
      password: '',
      base64auth: ''
    })
    history.push(ClientPaths.homeTemplateFilled())
  }

  isAuthenticated () {
    return this.state.base64auth !== ''
  }
}
