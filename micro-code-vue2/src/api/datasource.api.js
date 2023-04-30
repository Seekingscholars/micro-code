import {BaseApi} from '@/api/baseApi'
import request from '@/utils/request'

class Api extends BaseApi {
  constructor(requestUrl) {
    super(requestUrl)
  }
  /**
   * @param data
   */
  testConnection = (data) => {
    return request({ url: this.requestUrl + '/testConnection', method: 'post', data })
  };
}

export default new Api('/datasource')
