import React from 'react'
import HttpRequest from './http-request'
import ApiPaths from '../../utils/api-paths'

export default class extends React.Component {
  render () {
    return (
      <HttpRequest
        host={ApiPaths.getHost()}
        path='/'
        method={'GET'}
        onLoaded={(paths) => {
          ApiPaths.setPaths(paths)
          return this.props.render()
        }}
      />
    )
  }
}
