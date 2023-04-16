<template>
  <el-form-item label="默认值">
    <el-color-picker v-model="optionModel.defaultValue" @change="emitDefaultValueChange">
    </el-color-picker>
  </el-form-item>
</template>

<script>
import propertyMixin from '../propertyMixin'

export default {
  name: 'color-defaultValue-editor',
  mixins: [propertyMixin],
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
