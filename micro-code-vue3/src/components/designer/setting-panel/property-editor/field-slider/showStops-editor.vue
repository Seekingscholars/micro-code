<template>
  <el-form-item label="显示间断点">
    <el-switch v-model="optionModel.showStops"></el-switch>
  </el-form-item>
</template>

<script>
export default {
  name: 'showStops-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
