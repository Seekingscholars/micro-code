<template>
  <el-form-item label="允许半选">
    <el-switch v-model="optionModel.allowHalf"></el-switch>
  </el-form-item>
</template>
<script>
export default {
  name: 'allowHalf-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
