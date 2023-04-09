<template>
  <el-form-item label="显示关闭按钮">
    <el-switch v-model="optionModel.showClose"></el-switch>
  </el-form-item>
</template>

<script>
export default {
  name: 'showClose-editor',
  props: {
    designer: Object,
    selectedWidget: Object,
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
