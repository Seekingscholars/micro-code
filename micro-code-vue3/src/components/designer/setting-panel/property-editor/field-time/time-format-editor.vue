<template>
  <el-form-item label="显示格式">
    <el-select v-model="optionModel.format" allow-create filterable>
      <el-option label="HH:mm:ss" value="HH:mm:ss"></el-option>
      <el-option label="HH时mm分ss秒" value="HH时mm分ss秒"></el-option>
      <el-option label="hh:mm:ss" value="hh:mm:ss"></el-option>
    </el-select>
  </el-form-item>
</template>

<script>
export default {
  name: 'time-format-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
