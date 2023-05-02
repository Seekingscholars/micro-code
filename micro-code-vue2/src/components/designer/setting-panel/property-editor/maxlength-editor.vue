<template>
  <el-form-item label="最大长度">
    <el-input v-model="optionModel.maxlength" class="hide-spin-button" min="1" type="number"
    ></el-input>
  </el-form-item>
</template>

<script>

import propertyMixin from './propertyMixin'

export default {
  name: 'maxlength-editor',
  mixins: [propertyMixin],
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
