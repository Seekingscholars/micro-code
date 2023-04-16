<template>
  <el-form-item label="严格检查" prop="checkStrictly">
    <el-switch v-model="optionModel.checkStrictly"/>
  </el-form-item>
</template>

<script>
export default {
  name: 'tree-checkStrictly-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
