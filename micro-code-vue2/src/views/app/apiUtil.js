import restApi from '@/api/rest.api'
import request from '@/utils/request'
export const wrapWith = (event) => {
  return 'with(this.dataModel){\n' + event + '\n}'
}
const requestApi = (api, _this) => {
  return () => {
    const param = {
      url: api.url,
      method: api.method,
      data: api.data
    }
    if (api.param) {
      param.params = new Function(wrapWith('return {' + api.param + '}')).call(_this)
    }
    if (api.data) {
      param.data = new Function(wrapWith('return {' + api.data + '}')).call(_this)
    }
    if (api.timeout) {
      param.timeout = api.timeout
    }
    let successFunction
    if (api.successFunction) {
      successFunction = new Function('res', wrapWith(api.successFunction))
    }
    let failFunction
    if (api.failFunction) {
      failFunction = new Function('err', wrapWith(api.failFunction))
    }
    request(param, { headers: api.header }).then(res => {
      if (successFunction) {
        successFunction.call(_this, res)
      }
    }).catch(err => {
      if (failFunction) {
        failFunction.call(_this, err)
      }
    })
  }
}
export const getRest = async(formId, dataModel, _this) => {
  const apis = await restApi.list({ formId: formId }) || []
  for (const api of apis) {
    dataModel[api.name] = requestApi(api, _this)
  }
}
