<template>
  <el-form-item label="进度条宽度">
    <el-input v-model="optionModel.strokeWidth" type="number">
    </el-input>
  </el-form-item>
</template>
<script>
export default {
  name: 'progress-strokeWidth-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style lang="scss" scoped>

</style>
