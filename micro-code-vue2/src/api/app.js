import {BaseApi} from '@/api/baseApi'

class App extends BaseApi {
  constructor(requestUrl) {
    super(requestUrl)
  }
}

export default new App('/app')
