import React from 'react'
import { BrowserRouter as Router, Route, Link, Switch, Redirect } from 'react-router-dom'
import Login from './login'

// TODO remove
const Home = () => <h1>Home</h1>
const MenuItem1 = () => <h2>Item 1</h2>
const MenuItem2 = () => <h2>Item 2</h2>

/**
 * CSS Styles
 */
const menuStyle = {
  display: 'flex',
  backgroundColor: 'grey'
}

const iconStyle = {
  backgroundColor: 'black',
  color: 'white',
  width: '10%',
  textAlign: 'center',
  padding: '20px',
  textDecoration: 'none'
}

const menuItemStyle = {
  width: '5%',
  textAlign: 'center',
  padding: '20px',
  textDecoration: 'none',
  border: '2px solid dimgrey',
  borderRadius: '5px'
} 

export default class extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      username: '',
      password: '',
      base64auth: ''
    }
    this.isAuthenticated = this.isAuthenticated.bind(this)
  }

  authenticate (history, username, password) {
    this.setState({
      username: username,
      password: password,
      base64auth: Buffer.from(`${username}:${password}`).toString('base64')
    })
    history.push('/')
  }

  signout (history) {
    this.setState({
      username: '',
      password: '',
      base64auth: ''
    })
    history.push('/')
  }

  isAuthenticated () {
    return this.state.base64auth !== ''
  }

  render () {
    return (
      <Router>
        {this.menu()}
        <Switch>
          <PrivateRoute authFunction={this.isAuthenticated} path='/item1' component={MenuItem1} />
          <PrivateRoute authFunction={this.isAuthenticated} path='/item2' component={MenuItem2} />
          <Route path='/login' render={({ history }) =>
            <Login onSuccess={(username, password) => this.authenticate(history, username, password)} />
          } />
          <Route path='/logout' render={({ history }) => this.signout(history)} />
          <Route path='/' exact component={Home} />
          <Route path='/' render={({ location }) => (
            <h1>{location.pathname} Not Found</h1>
          )} />
        </Switch>
      </Router>
    )
  }

  menu () {
    return (
      <div style={menuStyle}>
        <Link style={iconStyle} to='/'>Project Manager</Link>
        <Link style={menuItemStyle} to='/item1'>Item 1</Link>
        <Link style={menuItemStyle} to='/item2'>Item 2</Link>
        {this.isAuthenticated()
          ? <Link style={menuItemStyle} to='/logout'>Logout</Link>
          : <Link style={menuItemStyle} to='/login'>Login</Link>}
      </div>
    )
  }
}

/**
 * Receives one object with:
 *  - authFunction
 *  - Component to show if the authFunction === true
 *  - Remaining route props
 */
function PrivateRoute ({ authFunction, component: Component, ...rest }) {
  return (
    <Route
      {...rest}
      render={props =>
        authFunction() ? (<Component {...props} />) : (
          <Redirect
            to={{
              pathname: '/login',
              state: { from: props.location }
            }}
          />
        )
      }
    />
  )
}
