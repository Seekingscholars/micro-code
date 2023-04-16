<template>
  <el-form-item label="是否为范围选择">
    <el-switch v-model="optionModel.range"></el-switch>
  </el-form-item>
</template>

<script>
export default {
  name: 'range-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
