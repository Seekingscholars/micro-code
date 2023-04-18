import { BaseApi } from '@/api/baseApi'
import request from '@/utils/request'

class AppApi extends BaseApi {
  constructor(requestUrl) {
    super(requestUrl)
  }
  listApp = (params) => {
    return request({ url: this.requestUrl + '/listApp', method: 'get', params })
  };
}
export default new AppApi('/app')
