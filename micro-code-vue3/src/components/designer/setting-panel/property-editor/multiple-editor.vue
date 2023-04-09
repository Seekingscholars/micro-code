<template>
  <el-form-item label="选项可多选">
    <el-switch v-model="optionModel.multiple" @change="onMultipleSelected"></el-switch>
  </el-form-item>
</template>

<script>

import propertyMixin from './propertyMixin'

export default {
  name: 'multiple-editor',
  mixins: [propertyMixin],
  props: {
    designer: Object,
    selectedWidget: Object,
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
