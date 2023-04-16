<template>
  <el-form-item label="显示">
    <el-switch v-model="optionModel.visible"></el-switch>
  </el-form-item>
</template>

<script>
export default {
  name: 'visible-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
