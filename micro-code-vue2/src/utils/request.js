import axios from 'axios'
import { Message } from 'element-ui'
import { getToken } from '@/utils/auth'
import store from '@/store'

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
// 对应国际化资源文件后缀
axios.defaults.headers['Content-Language'] = 'zh_CN'
// 创建axios实例
const service = axios.create({
  // axios中请求配置有baseURL选项，表示请求URL公共部分
  baseURL: process.env.VUE_APP_API,
  // 超时
  timeout: 10000,
  validateStatus: (status) => {
    return status >= 200
  }
})
function tansParams(params) {
  let result = ''
  for (const propName of Object.keys(params)) {
    const value = params[propName]
    const part = encodeURIComponent(propName) + '='
    if (value !== null && value !== '' && typeof (value) !== 'undefined') {
      if (!Array.isArray(value)) {
        if (typeof value === 'object') {
          for (const key of Object.keys(value)) {
            if (value[key] !== null && value[key] !== '' && typeof (value[key]) !== 'undefined') {
              const params = propName + '[' + key + ']'
              const subPart = encodeURIComponent(params) + '='
              result += subPart + encodeURIComponent(value[key]) + '&'
            }
          }
        } else {
          result += part + encodeURIComponent(value) + '&'
        }
      } else {
        result += value.map(v => part + encodeURIComponent(v)).join('&') + '&'
      }
    }
  }
  return result
}
// request拦截器
service.interceptors.request.use(config => {
  config.headers['X-Access-Token'] = getToken()
  // get请求映射params参数
  if (config.method === 'get' && config.params) {
    let url = config.url + '?' + tansParams(config.params)
    url = url.slice(0, -1)
    config.params = {}
    config.url = url
  }
  return config
}, error => {
  Promise.reject(error)
})

// 响应拦截器
service.interceptors.response.use(res => {
  if (res.config.origin) {
    return res.data
  }
  // 未设置状态码则默认成功状态
  const code = res.data.code || res.status

  // 获取错误信息
  const msg = res.data.message
  // 二进制数据则直接返回
  if (res.request.responseType === 'blob' || res.request.responseType === 'arraybuffer') {
    return res.data
  }
  if (msg) {
    Message({ message: msg, type: code === 200 ? 'success' : 'error' })
  }
  switch (code) {
    case 200:
      return res.data.result
    case 401:
      store.dispatch('user/logout').then(() => this.$router.push(`/login?redirect=${this.$route.fullPath}`))
      break
    case 404:
      Message({ message: res.data.path + '不存在', type: 'error' })
      break
  }
  return Promise.reject('error')
},
error => {
  console.log('err' + error)
  let { message } = error
  if (message === 'Network Error') {
    message = '后端接口连接异常'
  } else if (message.includes('timeout')) {
    message = '系统接口请求超时'
  } else if (message.includes('Request failed with status code')) {
    message = '系统接口' + message.substr(message.length - 3) + '异常'
  }
  Message({ message: message, type: 'error', duration: 5 * 1000 })
  return Promise.reject(error)
}
)

export default service
