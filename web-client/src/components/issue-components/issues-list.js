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
          onLoaded={(issues) => {
            const issuesProperties = EntitiesProperties.getProperties(issues, Rels.project_issue)
            return (
              <>
                <table>
                  <thead key='tableHead'>
                    <tr key='headerkey'>
                      <th key='id'>Id</th>
                      <th key='Name'>Name</th>
                      <th key='Description'>Description</th>
                    </tr>
                  </thead>
                  <tbody key='tableBody'>
                    {issuesProperties.map((properties) => {
                      return (
                        <tr key={properties.id}>
                          <td key={`id=${properties.id}`}>
                            <Link to={ClientPaths.issueTemplateFilled(properties.id)} >
                              {properties.id}
                            </Link>
                          </td>
                          <td key={`${properties.id}${properties.issueName}`}>{properties.issueName}</td>
                          <td key={`${properties.id}${properties.description}`}>{properties.description}</td>
                        </tr>
                      )
                    })}
                  </tbody>
                </table>
                <Link to={ClientPaths.issueCreateTemplateFilled(this.props.projectName)} >
                  <button>Add Issue</button>
                </Link>
              </>
            )
          }}
        />
      </>
    )
  }
}
