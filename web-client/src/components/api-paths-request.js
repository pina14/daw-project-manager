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
          console.log(paths)
          ApiInfo.setPaths(paths)
          console.log(ApiInfo.getPaths())
          return this.props.render()
        }}
      />
    )
  }
}
