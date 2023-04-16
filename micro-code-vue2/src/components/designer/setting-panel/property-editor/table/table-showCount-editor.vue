<template>
  <el-form-item label="显示数量">
    <el-input-number v-model="optionModel.showCount" style="width: 100%">
    </el-input-number>
  </el-form-item>
</template>

<script>

export default {
  name: 'table-showCount-editor',
  props: {
    designer: Object,
    selectedWidget: Object,
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
