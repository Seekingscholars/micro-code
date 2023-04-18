import {BaseApi} from '@/api/baseApi'

class AppApi extends BaseApi {
  constructor(requestUrl) {
    super(requestUrl)
  }
}

export default new AppApi('/app')
