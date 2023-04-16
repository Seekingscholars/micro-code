<template>
  <el-slider v-model="dataModel[field.options.name]" v-bind="field.options" v-on="getListeners(field)">
  </el-slider>
</template>

<script>

import fieldMixin from './fieldMixin'

export default {
  name: 'slider-widget',
  componentName: 'FieldWidget',
  mixins: [fieldMixin]
}
</script>

<style lang="scss" scoped>
.full-width-input {
  width: 100% !important;
}
</style>
