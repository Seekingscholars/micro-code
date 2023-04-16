<template>
  <div>
    <FormRender v-if="flushed" :form-json="formJson" />
  </div>
</template>
<script>
import FormRender from '@/components/render/index.vue'

export default {
  components: { FormRender },
  data() {
    return {
      formJson: {},
      flushed: false
    }
  },
  mounted() {
    const storage = window.sessionStorage
    const key = 'preview'
    const formValue = storage.getItem(key)
    if (formValue) {
      this.formJson = JSON.parse(formValue)
      this.flushed = true
    }
  }

}
</script>

<style scoped></style>
