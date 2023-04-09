<template>
  <el-form-item label="高亮当前行" prop="highlightCurrentRow">
    <el-switch v-model="optionModel.highlightCurrentRow"/>
  </el-form-item>
</template>

<script>
export default {
  name: 'table-highlightCurrentRow-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
