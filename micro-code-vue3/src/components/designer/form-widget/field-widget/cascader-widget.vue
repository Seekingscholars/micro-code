<template>
  <el-cascader v-model="dataModel[field.options.name]" class="full-width-input" v-bind="field.options"
               v-on="getListeners(field)">
  </el-cascader>
</template>
<script>
import fieldMixin from './fieldMixin'

export default {
  name: 'cascader-widget',
  componentName: 'FieldWidget',
  mixins: [fieldMixin]
}
</script>
<style lang="scss" scoped>
.full-width-input {
  width: 100% !important;
}
</style>
