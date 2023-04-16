<template>
  <el-form-item label="显示类型">
    <el-select v-model="optionModel.type" style="width: 100%">
      <el-option label="日期" value="date"></el-option>
      <el-option label="日期时间" value="datetime"></el-option>
      <el-option label="月份" value="month"></el-option>
      <el-option label="年份" value="year"></el-option>
      <el-option label="日期范围" value="daterange"></el-option>
      <el-option label="日期时间范围" value="datetimerange"></el-option>
      <el-option label="月份范围" value="monthrange"></el-option>
    </el-select>
  </el-form-item>
</template>
<script>
export default {
  name: 'date-type-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
