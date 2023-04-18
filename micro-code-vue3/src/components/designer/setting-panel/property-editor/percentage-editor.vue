<template>
  <el-form-item label="百分比">
    <el-input v-model="optionModel.percentage" :max="100" :min="0" type="number"></el-input>
  </el-form-item>
</template>
<script>
export default {
  name: 'percentage-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
