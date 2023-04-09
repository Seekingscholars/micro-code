<template>
  <el-form-item label="展开所有节点" prop="defaultExpandAll">
    <el-switch v-model="optionModel.defaultExpandAll"/>
  </el-form-item>
</template>

<script>
export default {
  name: 'tree-defaultExpandAll-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
