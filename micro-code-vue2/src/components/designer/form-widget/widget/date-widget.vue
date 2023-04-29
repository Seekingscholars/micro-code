<template>
  <el-date-picker v-model="dataModel[field.options.name]" class="full-width-input" v-bind="field.options"
                  v-on="getListeners(field)">
  </el-date-picker>
</template>
<script>
import fieldMixin from './fieldMixin'

export default {
  name: 'date-widget',
  componentName: 'FieldWidget',
  mixins: [fieldMixin]
}
</script>
<style lang="scss" scoped>
.full-width-input {
  width: 100% !important;
}
</style>
