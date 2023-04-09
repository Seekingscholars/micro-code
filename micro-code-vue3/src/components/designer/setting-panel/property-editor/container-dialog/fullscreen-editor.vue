<template>
  <el-form-item label="全屏">
    <el-switch v-model="optionModel.fullscreen"></el-switch>
  </el-form-item>
</template>

<script>
export default {
  name: 'fullscreen-editor',
  props: {
    designer: Object,
    selectedWidget: Object,
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
