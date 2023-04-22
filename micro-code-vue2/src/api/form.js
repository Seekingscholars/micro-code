import { BaseApi } from '@/api/baseApi'
import request from '@/utils/request'

class Form extends BaseApi {
  constructor(requestUrl) {
    super(requestUrl)
  }
  /**
   * @param params
   */
  getPermission = (params) => {
    return request({ url: this.requestUrl + '/getPermission', method: 'get', params })
  };
  /**
   * @param data
   */
  savePermission = (data) => {
    return request({ url: this.requestUrl + '/savePermission', method: 'post', data })
  };
  /**
   * @param params
   */
  getFormJson = (params,config={}) => {
    return request({ url: this.requestUrl + '/getFormJson', method: 'get', params, ...config })
  };
}

export default new Form('/form')
