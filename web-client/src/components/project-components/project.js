import React from 'react'
import HttpRequest from '../general-components/http-request'
import { Link } from 'react-router-dom'
import ClientPaths from '../../utils/client-paths'
import Actions from '../../utils/siren-actions'
import IssuesList from '../issue-components/issues-list'
import ProjectLabelsList from '../label-components/project-labels-list'
import ProjectStatesList from '../state-components/project-states-list'
import ProjectStateTransitionsList from '../state-transition-components/project-state-transitions-list'
import SubEntities from '../../utils/siren-sub-entities'
import Rels from '../../utils/rels'

export default class extends React.Component {
  render () {
    return (
      <>
        <h1>{this.props.projectName}</h1>
        <HttpRequest
          host={this.props.host}
          path={this.props.path}
          method={this.props.method}
          credentials={this.props.credentials}
          version={this.version}
          onLoaded={(project) => {
            return <>
              {this.renderProjectDetail(project)}
              <h2>Issues:</h2>
              {this.renderProjectIssues(project)}
              <h2>Authorized Labels for Issues:</h2>
              {this.renderProjectLabels(project)}
              <h2>Authorized States for Issues:</h2>
              {this.renderProjectStates(project)}
              <h2>Authorized State Transitions for Issues:</h2>
              {this.renderProjectStateTransitions(project)}
            </>
          }}
        />
      </>
    )
  }

  componentWillMount () {
    if (this.deleteRequest) this.deleteRequest.cancel()
  }

  renderProjectDetail (project) {
    return <>
      <div>
        <b>Name: </b>
        <p>{project.properties.name}</p>
      </div>
      <div>
        <b>Onwer: </b>
        <p>{project.properties.owner}</p>
      </div>
      <div>
        <b>Description: </b>
        <p>{project.properties.description}</p>
      </div>
      <div>
        <b>Default Issue State: </b>
        <p>{project.properties.defaultStateName}</p>
      </div>
      <div>
        <button onClick={() => this.Delete(project)}> Delete Project</button>
        <Link to={ClientPaths.projectUpdateTemplateFilled(this.props.projectName)} >
          <button>Update Project</button>
        </Link>
      </div>
    </>
  }

  renderProjectIssues (project) {
    return <IssuesList
      username={this.props.username}
      projectName={this.props.projectName}
      host={this.props.host}
      path={SubEntities.findByName(project, Rels.project_issues).href}
      method='GET'
      credentials={this.props.credentials} />
  }

  renderProjectLabels (project) {
    return <ProjectLabelsList
      username={this.props.username}
      projectName={this.props.projectName}
      host={this.props.host}
      path={SubEntities.findByName(project, Rels.project_labels).href}
      method='GET'
      credentials={this.props.credentials} />
  }

  renderProjectStates (project) {
    return <ProjectStatesList
      username={this.props.username}
      projectName={this.props.projectName}
      host={this.props.host}
      path={SubEntities.findByName(project, Rels.project_states).href}
      method='GET'
      credentials={this.props.credentials} />
  }

  renderProjectStateTransitions (project) {
    return <ProjectStateTransitionsList
      username={this.props.username}
      projectName={this.props.projectName}
      host={this.props.host}
      path={SubEntities.findByName(project, Rels.project_state_transitions).href}
      method='GET'
      credentials={this.props.credentials} />
  }

  Delete (project) {
    this.deleteRequest = Actions.call(
      project,
      'delete-project',
      this.props.host,
      undefined,
      this.props.credentials,
      this.props.onDelete,
      (error) => console.log(error)
    )
  }
}
