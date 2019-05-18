import React from 'react'
import { BrowserRouter as Router, Route, Link, Switch, Redirect } from 'react-router-dom'
import Login from '../session-components/login'
import Register from '../user-components/register'
import Profile from '../user-components/profile'
import UpdateUser from '../user-components/update-user'
import ProjectsList from '../project-components/projects-list'
import Project from '../project-components/project'
import Issue from '../issue-components/issue'
import UpdateProject from '../project-components/update-project'
import CreateProject from '../project-components/create-project'
import CreateIssue from '../issue-components/create-issue'
import CreateProjectLabel from '../label-components/create-project-label'
import CreateProjectState from '../state-components/create-project-state'
import CreateProjectStateTransition from '../state-transition-components/create-project-state-transition'
import AddIssueLabel from '../label-components/add-issue-label'
import ApiPaths from '../../utils/api-paths'
import ClientPaths from '../../utils/client-paths'

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
          <PrivateRoute authFunction={() => this.props.isAuthenticated()} path={ClientPaths.profileTemplate()} component={({ history }) =>
            <Profile
              username={this.props.username}
              host={this.host}
              path={ApiPaths.userUrl(this.props.username)}
              method='GET'
              credentials={this.props.base64auth}
              onDelete={() => this.props.signout(history)} />
          } exact />
          <PrivateRoute authFunction={() => this.props.isAuthenticated()} path={ClientPaths.profileUpdateTemplate()} component={({ history }) =>
            <UpdateUser
              username={this.props.username}
              host={this.host}
              path={ApiPaths.userUrl(this.props.username)}
              method='GET'
              credentials={this.props.base64auth}
              onSuccess={() => history.push(ClientPaths.profileTemplateFilled(this.props.username))} />
          } exact />
          <PrivateRoute authFunction={() => this.props.isAuthenticated()} path={ClientPaths.userProjectsTemplate()} component={({ history }) =>
            <ProjectsList
              username={this.props.username}
              host={this.host}
              path={ApiPaths.userProjectsUrl(this.props.username)}
              method='GET'
              credentials={this.props.base64auth} />
          } exact />
          <PrivateRoute authFunction={() => this.props.isAuthenticated()} path={ClientPaths.projectTemplate()} component={({ history, match }) =>
            <Project
              projectName={match.params.projectName}
              username={this.props.username}
              host={this.host}
              path={ApiPaths.projectUrl(match.params.projectName)}
              method='GET'
              credentials={this.props.base64auth}
              onDelete={() => history.push(ClientPaths.userProjectsTemplateFilled(this.props.username))} />
          } exact />
          <PrivateRoute authFunction={() => this.props.isAuthenticated()} path={ClientPaths.projectUpdateTemplate()} component={({ history, match }) =>
            <UpdateProject
              projectName={match.params.projectName}
              username={this.props.username}
              host={this.host}
              path={ApiPaths.projectUrl(match.params.projectName)}
              method='GET'
              credentials={this.props.base64auth}
              onSuccess={() => history.push(ClientPaths.projectTemplateFilled(match.params.projectName))} />
          } exact />
          <PrivateRoute authFunction={() => this.props.isAuthenticated()} path={ClientPaths.projectCreateTemplate()} component={({ history }) =>
            <CreateProject
              username={this.props.username}
              host={this.host}
              path={ApiPaths.userProjectsUrl()}
              method='POST'
              credentials={this.props.base64auth}
              onSuccess={() => history.push(ClientPaths.userProjectsTemplateFilled(this.props.username))} />
          } exact />
          <PrivateRoute authFunction={() => this.props.isAuthenticated()} path={ClientPaths.issueCreateTemplate()} component={({ history, match }) =>
            <CreateIssue
              username={this.props.username}
              projectName={match.params.projectName}
              host={this.host}
              path={ApiPaths.projectIssuesUrl()}
              method='POST'
              credentials={this.props.base64auth}
              onSuccess={() => history.push(ClientPaths.projectTemplateFilled(match.params.projectName))} />
          } exact />
          <PrivateRoute authFunction={() => this.props.isAuthenticated()} path={ClientPaths.issueTemplate()} component={({ history, match }) =>
            <Issue
              username={this.props.username}
              projectName={match.params.projectName}
              host={this.host}
              path={ApiPaths.issueUrl(match.params.issueId)}
              method='GET'
              credentials={this.props.base64auth}
              onDelete={() => history.push(ClientPaths.projectTemplateFilled(match.params.projectName))} />
          } exact />
          <PrivateRoute authFunction={() => this.props.isAuthenticated()} path={ClientPaths.projectLabelCreateTemplate()} component={({ history, match }) =>
            <CreateProjectLabel
              username={this.props.username}
              projectName={match.params.projectName}
              host={this.host}
              path={ApiPaths.projectLabelsUrl()}
              method='POST'
              credentials={this.props.base64auth}
              onSuccess={() => history.push(ClientPaths.projectTemplateFilled(match.params.projectName))} />
          } exact />
          <PrivateRoute authFunction={() => this.props.isAuthenticated()} path={ClientPaths.projectStateCreateTemplate()} component={({ history, match }) =>
            <CreateProjectState
              username={this.props.username}
              projectName={match.params.projectName}
              host={this.host}
              path={ApiPaths.projectStatesUrl()}
              method='POST'
              credentials={this.props.base64auth}
              onSuccess={() => history.push(ClientPaths.projectTemplateFilled(match.params.projectName))} />
          } exact />
          <PrivateRoute authFunction={() => this.props.isAuthenticated()} path={ClientPaths.projectStateTransitionCreateTemplate()} component={({ history, match }) =>
            <CreateProjectStateTransition
              username={this.props.username}
              projectName={match.params.projectName}
              host={this.host}
              path={ApiPaths.projectStateTransitionsUrl()}
              method='POST'
              credentials={this.props.base64auth}
              onSuccess={() => history.push(ClientPaths.projectTemplateFilled(match.params.projectName))} />
          } exact />
          <PrivateRoute authFunction={() => this.props.isAuthenticated()} path={ClientPaths.issueLabelAddTemplate()} component={({ history, match }) =>
            <AddIssueLabel
              projectName={match.params.projectName}
              issueId={match.params.issueId}
              host={this.host}
              path={ApiPaths.issueLabelsUrl()}
              method='POST'
              credentials={this.props.base64auth}
              onSuccess={() => history.push(ClientPaths.issueTemplateFilled(match.params.projectName, match.params.issueId))} />
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
