<template>
  <el-input-number v-model="dataModel[field.options.name]" class="full-width-input" v-bind="field.options"
                   v-on="getListeners(field)"
  >
  </el-input-number>
</template>

<script>
import fieldMixin from './fieldMixin'

export default {
  name: 'number-widget',
  componentName: 'FieldWidget',
  mixins: [fieldMixin]
}
</script>
<style lang="scss" scoped>
.full-width-input {
  width: 100% !important;
}
</style>
