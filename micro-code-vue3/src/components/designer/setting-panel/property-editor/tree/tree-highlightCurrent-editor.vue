<template>
  <el-form-item label="高亮当前节点" prop="highlightCurrent">
    <el-switch v-model="optionModel.highlightCurrent"/>
  </el-form-item>
</template>

<script>
export default {
  name: 'tree-highlightCurrent-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
