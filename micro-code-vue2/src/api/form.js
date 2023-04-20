import { BaseApi } from '@/api/baseApi'

class Form extends BaseApi {
  constructor(requestUrl) {
    super(requestUrl)
  }
}

export default new Form('/form')
