<template>
  <FormRender v-if="flushed" :form-json="formJson" :data-model="dataModel"/>
</template>
<script>
import FormRender from '@/components/render/index.vue'
import formApi from '@/api/form'
import { getRest } from '../apiUtil'
export default {
  components: { FormRender },
  data() {
    return {
      formJson: {},
      dataModel: {},
      flushed: false
    }
  },
  mounted() {
    const formId = this.$route.params.formId
    this.renderAppForm(formId)
  },
  methods: {
    async renderAppForm(formId) {
      await getRest(formId, this.dataModel, this)
      this.renderLocalForm(formId)
    },
    renderLocalForm(key) {
      const storage = window.sessionStorage
      const formValue = storage.getItem(key)
      if (formValue) {
        this.formJson = JSON.parse(formValue)
        this.flushed = true
      }
    }
  }

}
</script>
