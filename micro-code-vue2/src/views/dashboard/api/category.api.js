import { BaseApi } from '@/api/baseApi'
import request from '@/utils/request'

class CategoryApi extends BaseApi {
  constructor(requestUrl) {
    super(requestUrl)
  }

  listApp = (params) => {
    return request({ url: this.requestUrl + '/listApp', method: 'get', params })
  }

  saveBatch = (data) => {
    return request({ url: this.requestUrl + '/saveBatch', method: 'post', data })
  }
}

export default new CategoryApi('/category')
