import React from 'react'
import HttpRequest from '../general-components/http-request'
import { Link } from 'react-router-dom'
import ClientPaths from '../../utils/client-paths'
import Actions from '../../utils/siren-actions'
import Issues from '../issue-components/issues'
import SubEntities from '../../utils/siren-sub-entities'
import Rels from '../../utils/rels'

export default class extends React.Component {
  render () {
    return (
      <>
        <HttpRequest
          host={this.props.host}
          path={this.props.path}
          method={this.props.method}
          credentials={this.props.credentials}
          version={this.version}
          onLoaded={(project) => {
            return <>
              <h1>{this.props.projectName}</h1>
              {this.renderProjectDetail(project)}
              {this.renderProjectIssues(project)}
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
    return <Issues
      username={this.props.username}
      projectName={this.props.projectName}
      host={this.props.host}
      path={SubEntities.findByName(project, Rels.project_issues).href}
      method='GET'
      credentials={this.props.base64auth} />
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
