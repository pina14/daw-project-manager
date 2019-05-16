import React from 'react'
import Auth from './session-components/auth'
import ApiPathsRequest from './api-paths-request'

export default function (props) {
  return (
    <>
      <ApiPathsRequest render={() => { return <Auth /> }} />
    </>
  )
}
