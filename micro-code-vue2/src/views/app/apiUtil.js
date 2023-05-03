import restApi from '@/api/rest.api'
import datasourceApi from '@/api/datasource.api'
import request from '@/utils/request'
export const wrapWith = (event) => {
  return 'with(this.dataModel){\n' + event + '\n}'
}
const requestApi = (api, datasource, _this) => {
  return () => {
    const param = {
      baseURL: datasource.url,
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
    const headers = {}
    if (datasource.header) {
      for (const header of JSON.parse(datasource.header)) {
        headers[header.key] = header.value
      }
    }
    if (api.header) {
      const header = new Function(wrapWith('return {' + api.header + '}')).call(_this)
      param.headers = Object.assign(headers, header)
    }
    request(param).then(res => {
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
  const datasourceMap = {}
  for (const api of apis) {
    let datasource = datasourceMap[api.datasourceId]
    if (!datasource) {
      datasource = await datasourceApi.get({ id: api.datasourceId })
      datasourceMap[api.datasourceId] = datasource
    }
    dataModel[api.name] = requestApi(api, datasource, _this)
  }
}
