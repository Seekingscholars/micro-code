<template>
  <el-form-item label="节点多选" prop="showCheckbox">
    <el-switch v-model="optionModel.showCheckbox"/>
  </el-form-item>
</template>

<script>
export default {
  name: 'tree-showCheckbox-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
