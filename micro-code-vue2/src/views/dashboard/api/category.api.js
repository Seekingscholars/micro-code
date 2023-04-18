import { BaseApi } from '@/api/baseApi'
import request from '@/utils/request'

class AppApi extends BaseApi {
  constructor(requestUrl) {
    super(requestUrl)
  }
}
export default new AppApi('/category')
