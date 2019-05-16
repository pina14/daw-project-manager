import React from 'react'
import HttpRequest from '../http-request'
import Actions from '../../utils/siren-actions'
import Issues from '../issue-components/issues'
import SubEntities from '../../utils/siren-sub-entities'

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
        <button onClick={this.props.onUpdate}>Update Project</button>
      </div>
    </>
  }

  renderProjectIssues (project) {
    return <Issues
      username={this.props.username}
      host={this.props.host}
      path={SubEntities.findByName(project, '/rels/project-issues').href}
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
