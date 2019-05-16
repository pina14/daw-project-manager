import React from 'react'
import HttpRequest from './http-request'
import ApiInfo from '../utils/api-info'

export default class extends React.Component {
  render () {
    return (
      <HttpRequest
        host={ApiInfo.getHost()}
        path='/'
        method={'GET'}
        onLoaded={(paths) => {
          ApiInfo.setPaths(paths)
          return this.props.render()
        }}
      />
    )
  }
}
