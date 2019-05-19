import React from 'react'

export default class extends React.Component {
  render () {
    return <>
      <h1>Welcome to DAW Project Manager</h1>
      <h3>Functionalities:</h3>
      <ul>
        <li>Project creation and management.</li>
        <li>Creation of issues within a project.</li>
        <li>Assign labels and states to issues.</li>
        <li>Create comments to issues.</li>
      </ul>
    </>
  }
}