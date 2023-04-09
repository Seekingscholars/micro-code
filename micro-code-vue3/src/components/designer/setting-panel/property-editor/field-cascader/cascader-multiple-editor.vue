<template>
  <el-form-item label="选项可多选">
    <el-switch v-model="optionModel.multiple"></el-switch>
  </el-form-item>
</template>

<script>

import propertyMixin from '../propertyMixin'

export default {
  name: 'cascader-multiple-editor',
  mixins: [propertyMixin],
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
