<template>
  <el-color-picker v-model="dataModel[field.options.name]" v-bind="field.options" v-on="getListeners(field)">
  </el-color-picker>
</template>
<script>
import fieldMixin from './fieldMixin'
export default {
  name: 'color-widget',
  componentName: 'FieldWidget',
  mixins: [fieldMixin]
}
</script>
<style lang="scss" scoped>
</style>
