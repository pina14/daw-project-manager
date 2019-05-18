import React from 'react'
import HttpRequest from '../general-components/http-request'
import { Link } from 'react-router-dom'
import ClientPaths from '../../utils/client-paths'
import Actions from '../../utils/siren-actions'
import IssueLabelsList from '../label-components/issue-labels-list'
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
          onLoaded={(issue) => {
            return <>
              <h1>{issue.properties.issueName}</h1>
              {this.renderIssueDetail(issue)}
              <h2>Issue Labels:</h2>
              {this.renderIssueLabels(issue)}
              <h2>Comments:</h2>
              {this.renderIssueComments(issue)}
            </>
          }}
        />
      </>
    )
  }

  componentWillMount () {
    if (this.deleteRequest) this.deleteRequest.cancel()
  }

  renderIssueDetail (issue) {
    const properties = issue.properties
    return <>
      <div>
        <b>Id: </b>
        <p>{properties.id}</p>
      </div>
      <div>
        <b>Name: </b>
        <p>{properties.issueName}</p>
      </div>
      <div>
        <b>Project Name: </b>
        <p>{properties.projectName}</p>
      </div>
      <div>
        <b>Creator: </b>
        <p>{properties.issueCreator}</p>
      </div>
      <div>
        <b>Description: </b>
        <p>{properties.description}</p>
      </div>
      <div>
        <b>State: </b>
        <p>{properties.state}</p>
      </div>
      <div>
        <b>Creation Date: </b>
        <p>{properties.creationDate}</p>
      </div>
      <div>
        <button onClick={() => this.Delete(issue)}> Delete Issue</button>
        <Link to={ClientPaths.issueUpdateTemplateFilled(this.props.projectName, properties.id)} >
          <button>Update Issue</button>
        </Link>
      </div>
    </>
  }

  renderIssueLabels (issue) {
    return <IssueLabelsList
      username={this.props.username}
      projectName={this.props.projectName}
      issueId={issue.properties.id}
      host={this.props.host}
      path={SubEntities.findByName(issue, Rels.issue_labels).href}
      method='GET'
      credentials={this.props.credentials} />
  }

  renderIssueComments (issue) {

  }

  Delete (issue) {
    this.deleteRequest = Actions.call(
      issue,
      Actions.delete_issue,
      this.props.host,
      undefined,
      this.props.credentials,
      this.props.onDelete,
      (error) => console.log(error)
    )
  }
}
