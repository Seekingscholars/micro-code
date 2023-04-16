import request from '@/utils/request'

export function getCodeInfo(currdatetime) {
  return request({
    url: `/sys/randomImage/${currdatetime}`,
    method: 'get'
  })
}

export function login(data) {
  return request({
    url: '/sys/login',
    method: 'post',
    data
  })
}

export function getInfo() {
  return request({
    url: '/sys/user/getUserInfo',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: '/sys/logout',
    method: 'post'
  })
}
