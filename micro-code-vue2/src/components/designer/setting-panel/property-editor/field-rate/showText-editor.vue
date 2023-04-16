<template>
  <el-form-item label="显示文字">
    <el-switch v-model="optionModel.showText"></el-switch>
  </el-form-item>
</template>

<script>
export default {
  name: 'showText-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
