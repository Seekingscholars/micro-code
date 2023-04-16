<template>
  <el-button v-bind="field.options" v-on="getListeners(field)">
    {{ field.options.label }}
  </el-button>
</template>

<script>
import fieldMixin from './fieldMixin'
export default {
  name: 'button-widget',
  componentName: 'FieldWidget',
  mixins: [fieldMixin],
  props: {
    field: Object
  }
}
</script>

<style lang="scss" scoped>


</style>
