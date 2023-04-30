<template>
  <FormRender v-if="flushed" :form-json="formJson" />
</template>
<script>
import FormRender from '@/components/render/index.vue'
import '@/views/app/widget/component/index'
import formApi from '@/api/form'
import { Message, MessageBox } from 'element-ui'
import md5 from 'js-md5'
export default {
  components: { FormRender },
  data() {
    return {
      formJson: {},
      flushed: false
    }
  },
  mounted() {
    const formId = this.$route.params.formId
    const password = this.$route.query.password
    this.renderAppForm(formId, password)
  },
  methods: {
    async renderAppForm(formId, password) {
      const result = await formApi.getFormJson({ id: formId, password: password }, { origin: true })
      if (result.code === 200) {
        this.formJson = JSON.parse(result.result)
        this.flushed = true
      } else if (result.code === 1001) {
        const message = result.message
        const _this = this
        MessageBox.prompt('请输入提取码', '提示', {
          confirmButtonText: '确定',
          showCancelButton: false,
          showConfirmButton: false,
          showClose: false,
          showInput: true,
          closeOnPressEscape: false,
          closeOnClickModal: false,
          closeOnHashChange: false,
          inputErrorMessage: '提取码错误',
          inputValidator(value) {
            if (!value) {
              return false
            }
            value = value.trim()
            if (md5(md5(value) + value) === message) {
              formApi.getFormJson({ id: formId, password: value }).then(res => {
                _this.formJson = JSON.parse(res)
                _this.flushed = true
              })
              MessageBox.close()
              return true
            } else {
              return false
            }
          }
        })
      } else {
        Message({ message: result.message, type: 'error' })
      }
    }
  }

}
</script>

<style scoped></style>
