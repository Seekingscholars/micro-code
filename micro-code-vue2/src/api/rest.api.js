import {BaseApi} from '@/api/baseApi'

class Api extends BaseApi {
  constructor(requestUrl) {
    super(requestUrl)
  }
}

export default new Api('/api/rest')
