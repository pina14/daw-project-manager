import React from 'react'
import HttpRequest from '../general-components/http-request'

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
          onLoaded={(comment) => {
            const properties = comment.properties
            return <>
              <h1>{properties.issueName}</h1>
              <div>
                <b>Id: </b>
                <p>{properties.id}</p>
              </div>
              <div>
                <b>Creator: </b>
                <p>{properties.commentCreator}</p>
              </div>
              <div>
                <b>Issue Id: </b>
                <p>{properties.issueId}</p>
              </div>
              <div>
                <b>Content: </b>
                <p>{properties.content}</p>
              </div>
              <div>
                <b>Creation Date: </b>
                <p>{properties.creationDate}</p>
              </div>
            </>
          }}
        />
      </>
    )
  }
}
