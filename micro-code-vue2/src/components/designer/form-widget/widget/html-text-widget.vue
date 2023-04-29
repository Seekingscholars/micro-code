<template>
  <div v-html="field.options.htmlContent"></div>
</template>

<script>
import fieldMixin from './fieldMixin'

export default {
  name: 'html-text-widget',
  componentName: 'FieldWidget',
  mixins: [fieldMixin],
  props: {
    field: Object,
    parentWidget: Object,
    parentList: Array,
    indexOfParentList: Number,
    designer: Object

  },

  created() {

  }

}
</script>

<style lang="scss" scoped>


</style>
