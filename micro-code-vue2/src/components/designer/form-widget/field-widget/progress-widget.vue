<template>
  <el-progress v-bind="field.options"></el-progress>
</template>
<script>
import fieldMixin from './fieldMixin'

export default {
  name: 'progress-widget',
  componentName: 'FieldWidget',
  mixins: [fieldMixin]
}
</script>
<style lang="scss" scoped>
</style>
