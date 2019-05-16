import React from 'react'
import { BrowserRouter as Router, Route, Link, Switch, Redirect } from 'react-router-dom'
import Login from './login'
import Register from './register'
import Profile from './profile'
import UpdateUser from './update-user'
import Projects from './projects'
import Project from './project'
import UpdateProject from './update-project'
import ApiInfo from '../utils/api-info'

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
  constructor (props) {
    super(props)
    this.host = ApiInfo.getHost()
  }

  render () {
    return (
      <Router>
        {this.menu()}
        <Switch>
          <PrivateRoute authFunction={() => this.props.isAuthenticated()} path='/profile/:username' component={({ history, match }) =>
            <Profile
              username={this.props.username}
              host={this.host}
              path={ApiInfo.userUrl(match.params.username)}
              method='GET'
              credentials={this.props.base64auth}
              onDelete={() => this.props.signout(history)}
              onUpdate={() => history.push(`/profile/${this.props.username}/update`)} />
          } exact />
          <PrivateRoute authFunction={() => this.props.isAuthenticated()} path='/profile/:username/update' component={({ history, match }) =>
            <UpdateUser
              username={this.props.username}
              host={this.host}
              path={ApiInfo.userUrl(match.params.username)}
              method='GET'
              credentials={this.props.base64auth}
              onSuccess={() => history.push(`/profile/${this.props.username}`)} />
          } exact />
          <PrivateRoute authFunction={() => this.props.isAuthenticated()} path='/users/:username/projects' component={({ history }) =>
            <Projects
              username={this.props.username}
              host={this.host}
              path={ApiInfo.userProjectsUrl(this.props.username)}
              method='GET'
              credentials={this.props.base64auth} />
          } exact />
          <PrivateRoute authFunction={() => this.props.isAuthenticated()} path='/projects/:projectName' component={({ history, match }) =>
            <Project
              projectName={match.params.projectName}
              username={this.props.username}
              host={this.host}
              path={ApiInfo.projectUrl(match.params.projectName)}
              method='GET'
              credentials={this.props.base64auth}
              onDelete={() => history.push(`/users/${this.props.username}/projects`)}
              onUpdate={() => history.push(`/projects/${match.params.projectName}/update`)}
            />
          } exact />
          <PrivateRoute authFunction={() => this.props.isAuthenticated()} path='/projects/:projectName/update' component={({ history, match }) =>
            <UpdateProject
              projectName={match.params.projectName}
              username={this.props.username}
              host={this.host}
              path={ApiInfo.projectUrl(match.params.projectName)}
              method='GET'
              credentials={this.props.base64auth}
              onSuccess={() => history.push(`/projects/${match.params.projectName}`)} />
          } exact />
          <Route path='/login' render={({ history }) =>
            <Login
              host={this.host}
              path={ApiInfo.authenticateUrl()}
              method='POST'
              onSuccess={(username, password, base64auth) => this.props.authenticate(history, username, password, base64auth)}
            />
          } exact />
          <Route path='/register' render={({ history }) =>
            <Register
              host={this.host}
              path={ApiInfo.usersUrl}
              method='POST'
              onSuccess={() => history.push('/login')}
            />
          } exact />
          <Route path='/logout' render={({ history }) => this.props.signout(history)} exact />
          <Route path='/' component={Home} exact />
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
              pathname: '/login'
            }}
          />
        )
      }
    />
  )
}
