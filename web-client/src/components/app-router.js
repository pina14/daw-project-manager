import React from 'react'
import { BrowserRouter as Router, Route, Link, Switch, Redirect } from 'react-router-dom'
import Login from './login'
import Profile from './profile'
import Register from './register'
import Projects from './projects'
import Project from './project'

const Home = () => <h1>Home</h1>

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
  render () {
    return (
      <Router>
        {this.menu()}
        <Switch>
          <PrivateRoute authFunction={() => this.props.isAuthenticated()} path='/profile/:username' component={({ history, match }) =>
            <Profile
              username={this.props.username}
              host='http://localhost:8090'
              path={`/users/${match.params.username}`}
              method='GET'
              credentials={this.props.base64auth}
              onDelete={() => this.props.signout()} />
          } />
          <PrivateRoute authFunction={() => this.props.isAuthenticated()} path='/users/:username/projects' component={({ history }) =>
            <Projects
              username={this.props.username}
              host='http://localhost:8090'
              path='/projects'
              method='GET'
              credentials={this.props.base64auth} />
          } />
          <PrivateRoute authFunction={() => this.props.isAuthenticated()} path='/projects/:projectName' component={({ history, match }) =>
            <Project
              projectName={match.params.projectName}
              username={this.props.username}
              host='http://localhost:8090'
              path={`/projects/${match.params.projectName}`}
              method='GET'
              credentials={this.props.base64auth}
            />
          } />
          <Route path='/login' render={({ history }) =>
            <Login
              host='http://localhost:8090'
              path='/users/authenticate'
              method='POST'
              onSuccess={(username, password, base64auth) => this.props.authenticate(history, username, password, base64auth)}
            />
          } />
          <Route path='/register' render={({ history }) =>
            <Register
              host='http://localhost:8090'
              path='/users'
              method='POST'
              onSuccess={() => history.push('/login')}
            />
          } />
          <Route path='/logout' render={({ history }) => this.props.signout(history)} />
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
        <Link style={menuItemStyle}
          to={`/profile/${this.props.username ? this.props.username : '*'}`}>Profile</Link>
        <Link style={menuItemStyle}
          to={`/users/${this.props.username ? this.props.username : '*'}/projects`}>Projects</Link>
        {this.props.isAuthenticated()
          ? <Link style={menuItemStyle} to='/logout'>Logout</Link>
          : (
            <>
              <Link style={menuItemStyle} to='/login'>Login</Link>
              <Link style={menuItemStyle} to='/register'>Register</Link>
            </>
          )
        }
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
