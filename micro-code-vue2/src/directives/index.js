import {sort} from './sort'

const install = function (Vue) {
  Vue.directive('sort', sort)
}

export default install
