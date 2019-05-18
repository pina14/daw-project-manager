import React from 'react'
import HttpRequest from '../general-components/http-request'
import { Link } from 'react-router-dom'
import ClientPaths from '../../utils/client-paths'
import EntitiesProperties from '../../utils/siren-entities-properties'
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
          onLoaded={(comments) => {
            const commentsProperties = EntitiesProperties.getProperties(comments, Rels.issue_comment)
            return (
              <>
                <table>
                  <thead key='tableHead'>
                    <tr key='headerkey'>
                      <th key='Id'>Comment ID</th>
                      <th key='Content'>Content</th>
                    </tr>
                  </thead>
                  <tbody key='tableBody'>
                    {commentsProperties.map((properties) => {
                      return (
                        <tr key={properties.id}>
                          <td key={`id=${properties.id}`}>
                            <Link to={ClientPaths.issueCommentTemplateFilled(this.props.projectName, this.props.issueId, properties.id)} >
                              {properties.id}
                            </Link>
                          </td>
                          <td key={`content=${properties.content}`}>
                            {properties.content}
                          </td>
                        </tr>
                      )
                    })}
                  </tbody>
                </table>
                <Link to={ClientPaths.issueCommentCreateTemplateFilled(this.props.projectName, this.props.issueId)} >
                  <button>Add Issue Comment</button>
                </Link>
              </>
            )
          }}
        />
      </>
    )
  }
}
