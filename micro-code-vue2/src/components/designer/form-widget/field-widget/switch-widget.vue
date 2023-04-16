<template>
  <el-switch v-model="dataModel[field.options.name]" v-bind="field.options" v-on="getListeners(field)">
  </el-switch>
</template>
<script>
import fieldMixin from './fieldMixin'

export default {
  name: 'switch-widget',
  componentName: 'FieldWidget',
  mixins: [fieldMixin]
}
</script>
<style lang="scss" scoped>
.full-width-input {
  width: 100% !important;
}
</style>
