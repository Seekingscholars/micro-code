<template>
  <FormRender v-if="flushed" :form-json="formJson" />
</template>
<script>
import FormRender from '@/components/render/index.vue'
import formApi from '@/api/form'
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
    if (formId && formId !== '0') {
      this.renderAppForm(formId)
    } else {
      this.renderLocalForm()
    }
  },
  methods: {
    async renderAppForm(formId) {
      const form = await formApi.get({ id: formId })
      if (form) {
        this.formJson = JSON.parse(form.formJson)
        this.flushed = true
      }
    },
    renderLocalForm() {
      const storage = window.sessionStorage
      const key = 'preview'
      const formValue = storage.getItem(key)
      if (formValue) {
        this.formJson = JSON.parse(formValue)
        this.flushed = true
      }
    }
  }

}
</script>

<style scoped></style>
