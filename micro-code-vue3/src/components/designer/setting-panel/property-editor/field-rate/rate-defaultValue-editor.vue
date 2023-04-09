<template>
  <el-form-item label="默认值">
    <el-input-number v-model="optionModel.defaultValue" :max="optionModel.max" :min="0" style="width: 100%"
                     @change="emitDefaultValueChange"
    >
    </el-input-number>
  </el-form-item>
</template>

<script>

import propertyMixin from '../propertyMixin'

export default {
  name: 'rate-defaultValue-editor',
  mixins: [propertyMixin],
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
