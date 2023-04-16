<template>
  <el-form-item label="类型">
    <el-select v-model="optionModel.type" style="width: 100%">
      <el-option label="线条" value="line"></el-option>
      <el-option label="圆环" value="circle"></el-option>
      <el-option label="仪表盘" value="dashboard"></el-option>
    </el-select>
  </el-form-item>
</template>
<script>
export default {
  name: 'progress-type-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style lang="scss" scoped>

</style>
