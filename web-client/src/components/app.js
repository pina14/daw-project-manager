import React from 'react'
import Auth from './auth'
import ApiPathsRequest from './api-paths-request'

export default function (props) {
  return (
    <>
      <ApiPathsRequest render={() => { return <Auth /> }} />
    </>
  )
}
