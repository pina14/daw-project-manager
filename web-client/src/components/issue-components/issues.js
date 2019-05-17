import React from 'react'
import HttpRequest from '../general-components/http-request'
import { Link } from 'react-router-dom'
import ClientPaths from '../../utils/client-paths'

export default class extends React.Component {
  render () {
    return (
      <>
        <h2>Issues:</h2>
        <HttpRequest
          host={this.props.host}
          path={this.props.path}
          method={this.props.method}
          credentials={this.props.credentials}
          onLoaded={(issues) => {
            return (
              <>
                <table>
                  <thead key='tableHead'>
                    <tr key='headerkey'>
                      <th key='id'>Id</th>
                      <th key='Creator'>Creator</th>
                      <th key='Name'>Name</th>
                    </tr>
                  </thead>
                  <tbody key='tableBody'>
                    {issues.entities.map((issue) => {
                      const properties = issue.properties
                      return (
                        <tr key={properties.id}>
                          <td key={`id=${properties.id}`}>
                            <Link to={ClientPaths.issueTemplateFilled(properties.id)} >
                              {properties.id}
                            </Link>
                          </td>
                          <td key={`${properties.id}${properties.issueCreator}`}>{properties.issueCreator}</td>
                          <td key={`${properties.id}${properties.issueName}`}>{properties.issueName}</td>
                        </tr>
                      )
                    })}
                  </tbody>
                </table>
              </>
            )
          }}
        />
      </>
    )
  }
}
