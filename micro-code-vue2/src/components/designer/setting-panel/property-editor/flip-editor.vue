<template>
  <el-form-item label="轴翻转">
    <el-switch v-model="optionModel.flip"></el-switch>
  </el-form-item>
</template>
<script>
export default {
  name: 'flip-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
