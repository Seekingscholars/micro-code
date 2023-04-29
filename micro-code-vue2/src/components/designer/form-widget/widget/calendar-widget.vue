<template>
  <el-calendar v-model="dataModel[field.options.name]">
  </el-calendar>
</template>
<script>
import fieldMixin from './fieldMixin'

export default {
  name: 'calendar-widget',
  componentName: 'FieldWidget',
  mixins: [fieldMixin]
}
</script>
<style lang="scss" scoped>
</style>
