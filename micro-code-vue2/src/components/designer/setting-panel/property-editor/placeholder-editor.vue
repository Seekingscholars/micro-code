<template>
  <el-form-item label="占位内容">
    <el-input v-model="optionModel.placeholder" type="text"></el-input>
  </el-form-item>
</template>

<script>

import propertyMixin from './propertyMixin'

export default {
  name: 'placeholder-editor',
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
