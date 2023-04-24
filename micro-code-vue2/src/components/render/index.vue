<template>
  <FormWidget :designer="this.formJson"></FormWidget>
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
    }
  },
  provide() {
    return {
      $model: this.dataModel
    }
  },
  data() {
    return {
      dataModel: {}
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
