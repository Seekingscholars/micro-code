import request from '@/utils/request'

export class BaseApi {
  requestUrl = '';

  constructor(requestUrl) {
    this.requestUrl = requestUrl
  }

  /**
   * @param params
   */
  get = (params) => {
    return request({ url: this.requestUrl + '/get', method: 'get', params })
  };
  /**
   * @param params
   */
  getOne = (params) => {
    return request({ url: this.requestUrl + '/getOne', method: 'get', params })
  };
  /**
   * @param params
   */
  page = (params) => {
    return request({ url: this.requestUrl + '/page', method: 'get', params })
  };
  /**
   *
   * @param params
   */
  list = (params) => {
    return request({ url: this.requestUrl + '/list', method: 'get', params })
  };
  /**
   * @param params
   */
  save = (data) => {
    return request({ url: this.requestUrl + '/save', method: 'post', data })
  };
  /**
   * @param params
   */
  postEdit = (data) => {
    return request({ url: this.requestUrl + '/edit', method: 'post', data })
  };
  /**
   * @param params
   */
  putEdit = (data) => {
    return request({ url: this.requestUrl + '/edit', method: 'put', data })
  };
  /**
   * @param params
   */
  saveOrEdit = (data, isUpdate) => {
    if (isUpdate) {
      return this.postEdit(data)
    } else {
      return this.save(data)
    }
  };
  /**
   * @param params
   */
  remove = (params) => {
    return request(
      { url: this.requestUrl + '/remove', method: 'delete', params }
    )
  };

  /**
   * @param params
   */
  removeBatch = (params, handleSuccess) => {
    this.$confirm('是否删除选中数据, 是否继续?', '警告', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }).then(() => {
      return request(
        { url: this.requestUrl + '/removeBatch', method: 'get', params: params }
      )
    })
  };
}
