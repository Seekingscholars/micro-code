<template>
  <FormWidget :designer="formJson"></FormWidget>
</template>
<script>
import FormWidget from '../designer/form-widget/FormWidget'

export default {
  name: 'FormRender',
  componentName: 'VFormRender',
  components: {
    FormWidget
  },
  props: {
    formJson: {
      type: Object
    },
    dataModel: {
      required: false,
      default: {}
    }
  },
  provide() {
    return {
      $model: this.dataModel
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
