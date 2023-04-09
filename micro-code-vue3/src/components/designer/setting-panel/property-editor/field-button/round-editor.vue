<template>
  <el-form-item label="圆角按钮">
    <el-switch v-model="optionModel.round"></el-switch>
  </el-form-item>
</template>

<script>


export default {
  name: 'round-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
