<template>
  <el-input v-model="dataModel[field.options.name]" v-bind="field.options" v-on="getListeners(field)">
  </el-input>
</template>
<script>
import fieldMixin from './fieldMixin'

export default {
  name: 'input-widget',
  componentName: 'FieldWidget',
  mixins: [fieldMixin]
}
</script>
<style lang="scss" scoped>


</style>
