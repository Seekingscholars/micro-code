<template>
  <el-form-item label="默认值">
    <el-time-picker v-model="optionModel.defaultValue" :format="optionModel.format" is-range
                    style="width: 100%" value-format="HH:mm:ss" @change="emitDefaultValueChange"
    >
    </el-time-picker>
  </el-form-item>
</template>

<script>

import propertyMixin from '../propertyMixin'

export default {
  name: 'time-range-defaultValue-editor',
  mixins: [propertyMixin],
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
