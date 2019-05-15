import React from 'react'
import Request from '../utils/cancelable-request'

const FetchState = {
  loading: 'loading',
  loaded: 'loaded',
  error: 'error'
}

export default class extends React.Component {
  constructor (props) {
    super(props)
    this.key = 0
    this.state = {
      loadState: FetchState.loading
    }
  }

  componentDidMount () {
    this.load(this.props.host, this.props.path, this.props.query, this.props.method, this.props.credentials)
  }

  componentDidUpdate (prevProps) {
    if (this.props.path !== prevProps.path || this.props.version > prevProps.version) {
      this.load(this.props.host, this.props.path, this.props.query, this.props.method, this.props.credentials)
    }
  }

  componentWillUnmount () {
    if (this.request) this.request.cancel()
  }

  render () {
    switch (this.state.loadState) {
      case FetchState.loading: return this.renderLoading()
      case FetchState.loaded: return this.renderLoaded()
      case FetchState.error: return this.renderError()
    }
  }

  renderLoading () {
    return this.props.onLoading ? this.props.onLoading(this.state.json) : <div>Loading...</div>
  }

  renderLoaded () {
    return this.props.onLoaded(this.state.json)
  }

  renderError () {
    return this.props.onError ? this.props.onError(this.state.error) : <div>{this.state.error.message}</div>
  }

  load (host, path, query, method, credentials) {
    if (this.request) {
      this.request.cancel()
      this.setState({
        loadState: FetchState.loading
      })
    }
    this.request = new Request(
      host,
      path,
      query,
      method,
      (json) => this.setState({
        loadState: FetchState.loaded,
        json: json
      }),
      (error) => this.setState({
        loadState: FetchState.error,
        error: error
      })
    )

    this.request.setHeaders({ 'Authorization': `Basic ${credentials}` })
    this.request.send()
  }
}
