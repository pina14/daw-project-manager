
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
    this.onSuccess = onSuccess.bind(this)
    this.onError = onError.bind(this)
    this.isActive = true
  }

  setHeaders (headers) {
    this.headers = headers
  }

  setBody (body) {
    this.body = body
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
        if (res.body) {
          let json = await res.json()
          this.callbackIfActive(this.onSuccess, json)
        } else {
          this.callbackIfActive(this.onSuccess, {})
        }
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
