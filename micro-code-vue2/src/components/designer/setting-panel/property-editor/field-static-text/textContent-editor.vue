<template>
  <el-form-item label="i静态文字">
    <el-input v-model="optionModel.textContent"></el-input>
  </el-form-item>
</template>

<script>
export default {
  name: 'textContent-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
