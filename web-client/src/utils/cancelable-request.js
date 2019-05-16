
/**
 * url -> Url to request
 * method -> Method of request
 * body -> Body of the request
 * headers -> Headers of the request
 * onSuccess ->  Function to call if request is successful
 * onError -> Function to call if request fails
 */
export default class {
  constructor (host, path, method, onSuccess, onError) {
    this.url = host + path
    this.method = method
    this.headers = {}
    this.onSuccess = onSuccess.bind(this)
    this.onError = onError.bind(this)
    this.isActive = true
  }

  setHeaders (headers) {
    Object.assign(this.headers, headers)
  }

  /**
   * Add header content-Type to application/json and set body
   */
  setBody (body) {
    this.headers['Content-Type'] = 'application/json'
    this.body = JSON.stringify(body)
  }

  async send () {
    try {
      const reqData = {
        method: this.method,
        headers: this.headers,
        body: this.body
      }
      let res = await fetch(this.url, reqData)
      if (res.status === 200 || res.status === 201) {
        let json = await res.json()
        this.callbackIfActive(this.onSuccess, json)
      } else {
        this.callbackIfActive(this.onError, { message: `non-200 status (${res.status})` })
      }
    } catch (error) {
      this.callbackIfActive(this.onError, error)
    }
  }

  callbackIfActive (cb, data) {
    if (this.isActive) cb(data)
  }

  cancel () {
    this.isActive = false
  }
}
