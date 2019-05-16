import React from 'react'
import { Link } from 'react-router-dom'
import HttpRequest from '../http-request'
import ClientPaths from '../../utils/client-paths'

export default class extends React.Component {
  render () {
    return (
      <>
        <h1>Projects</h1>
        <HttpRequest
          host={this.props.host}
          path={this.props.path}
          method={this.props.method}
          credentials={this.props.credentials}
          onLoaded={(projects) => {
            return (
              <>
                <table>
                  <thead key='tableHead'>
                    <tr key='headerkey'>
                      <th key='Name'>Name</th>
                      <th key='Owner'>Owner</th>
                      <th key='Description'>Description</th>
                      <th key='Default Issue State'>Default State</th>
                    </tr>
                  </thead>
                  <tbody key='tableBody'>
                    {projects.entities.map((project) => {
                      const properties = project.properties
                      return (
                        <tr key={properties.name}>
                          <td key={`projectName=${properties.name}`}>
                            <Link to={ClientPaths.projectTemplateFilled(properties.name)}>
                              {properties.name}
                            </Link>
                          </td>
                          <td key={`owner=${properties.name}${properties.owner}`}>{properties.owner}</td>
                          <td key={`owner=${properties.name}${properties.description}`}>{properties.description}</td>
                          <td key={`owner=${properties.name}${properties.defaultStateName}`}>{properties.defaultStateName}</td>
                        </tr>
                      )
                    })}
                  </tbody>
                </table>
                <button onClick={this.props.onAdd}>Add Project</button>
              </>
            )
          }}
        />
      </>
    )
  }
}
