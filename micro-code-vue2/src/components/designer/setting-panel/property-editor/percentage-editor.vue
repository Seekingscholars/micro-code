<template>
  <el-form-item label="百分比">
    <el-input type="number" v-model="optionModel.percentage" :min="0" :max="100"></el-input>
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
