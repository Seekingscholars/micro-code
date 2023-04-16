<template>
  <el-form-item label="圆形按钮">
    <el-switch v-model="optionModel.circle"></el-switch>
  </el-form-item>
</template>

<script>
export default {
  name: 'circle-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
