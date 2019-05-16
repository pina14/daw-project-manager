import React from 'react'
import { BrowserRouter as Router, Route, Link, Switch, Redirect } from 'react-router-dom'
import Login from './session-components/login'
import Register from './user-components/register'
import Profile from './user-components/profile'
import UpdateUser from './user-components/update-user'
import Projects from './project-components/projects'
import Project from './project-components/project'
import UpdateProject from './project-components/update-project'
import CreateProject from './project-components/create-project'
import ApiPaths from '../utils/api-paths'
import ClientPaths from '../utils/client-paths'

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
    this.host = ApiPaths.getHost()
  }

  render () {
    return (
      <Router>
        {this.menu()}
        <Switch>
          <PrivateRoute authFunction={() => this.props.isAuthenticated()} path={ClientPaths.profileTemplate()} component={({ history, match }) =>
            <Profile
              username={match.params.username}
              host={this.host}
              path={ApiPaths.userUrl(match.params.username)}
              method='GET'
              credentials={this.props.base64auth}
              onDelete={() => this.props.signout(history)}
              onUpdate={() => history.push(ClientPaths.profileUpdateTemplateFilled(match.params.username))} />
          } exact />
          <PrivateRoute authFunction={() => this.props.isAuthenticated()} path={ClientPaths.profileUpdateTemplate()} component={({ history, match }) =>
            <UpdateUser
              username={match.params.username}
              host={this.host}
              path={ApiPaths.userUrl(match.params.username)}
              method='GET'
              credentials={this.props.base64auth}
              onSuccess={() => history.push(ClientPaths.profileTemplateFilled(match.params.username))} />
          } exact />
          <PrivateRoute authFunction={() => this.props.isAuthenticated()} path={ClientPaths.userProjectsTemplate()} component={({ history, match }) =>
            <Projects
              username={match.params.username}
              host={this.host}
              path={ApiPaths.userProjectsUrl(match.params.username)}
              method='GET'
              credentials={this.props.base64auth}
              onAdd={() => history.push(ClientPaths.projectCreateTemplateFilled(match.params.username))} />
          } exact />
          <PrivateRoute authFunction={() => this.props.isAuthenticated()} path={ClientPaths.projectTemplate()} component={({ history, match }) =>
            <Project
              projectName={match.params.projectName}
              username={match.params.username}
              host={this.host}
              path={ApiPaths.projectUrl(match.params.projectName)}
              method='GET'
              credentials={this.props.base64auth}
              onDelete={() => history.push(ClientPaths.userProjectsTemplateFilled(match.params.username))}
              onUpdate={() => history.push(ClientPaths.projectUpdateTemplateFilled(match.params.projectName))}
            />
          } exact />
          <PrivateRoute authFunction={() => this.props.isAuthenticated()} path={ClientPaths.projectUpdateTemplate()} component={({ history, match }) =>
            <UpdateProject
              projectName={match.params.projectName}
              username={match.params.username}
              host={this.host}
              path={ApiPaths.projectUrl(match.params.projectName)}
              method='GET'
              credentials={this.props.base64auth}
              onSuccess={() => history.push(ClientPaths.projectTemplateFilled(match.params.projectName))} />
          } exact />
          <PrivateRoute authFunction={() => this.props.isAuthenticated()} path={ClientPaths.projectCreateTemplate()} component={({ history, match }) =>
            <CreateProject
              username={match.params.username}
              host={this.host}
              path={ApiPaths.userProjectsUrl(match.params.username)}
              method='POST'
              credentials={this.props.base64auth}
              onSuccess={() => history.push(ClientPaths.userProjectsTemplateFilled(match.params.username))} />
          } exact />
          <Route path={ClientPaths.loginTemplate()} render={({ history }) =>
            <Login
              host={this.host}
              path={ApiPaths.authenticateUrl()}
              method='POST'
              onSuccess={(username, password, base64auth) => this.props.authenticate(history, username, password, base64auth)}
            />
          } exact />
          <Route path={ClientPaths.registerTemplate()} render={({ history }) =>
            <Register
              host={this.host}
              path={ApiPaths.usersUrl()}
              method='POST'
              onSuccess={() => history.push(ClientPaths.loginTemplateFilled())}
            />
          } exact />
          <Route path={ClientPaths.logoutTemplate()} render={({ history }) => this.props.signout(history)} exact />
          <Route path={ClientPaths.homeTemplate()} component={Home} exact />
          <Route path={ClientPaths.homeTemplate()} render={({ location }) => (
            <h1>{location.pathname} Not Found</h1>
          )} />
        </Switch>
      </Router>
    )
  }

  menu () {
    return (
      <div style={menuStyle}>
        <Link style={iconStyle} to={ClientPaths.homeTemplateFilled()}>Project Manager</Link>
        <Link style={menuItemStyle} to={ClientPaths.profileTemplateFilled(this.props.username)}>Profile</Link>
        <Link style={menuItemStyle} to={ClientPaths.userProjectsTemplateFilled(this.props.username)}>Projects</Link>
        {this.props.isAuthenticated()
          ? <Link style={menuItemStyle} to={ClientPaths.logoutTemplateFilled()}>Logout</Link>
          : (
            <>
              <Link style={menuItemStyle} to={ClientPaths.loginTemplateFilled()}>Login</Link>
              <Link style={menuItemStyle} to={ClientPaths.registerTemplateFilled()}>Register</Link>
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
              pathname: ClientPaths.loginTemplateFilled()
            }}
          />
        )
      }
    />
  )
}
