import { BaseApi } from '@/api/baseApi'

class FormApi extends BaseApi {
  constructor(requestUrl) {
    super(requestUrl)
  }
}

export default new FormApi('/form')
