<template>
  <el-form-item label="内部文字">
    <el-switch v-model="optionModel.textInside"></el-switch>
  </el-form-item>
</template>
<script>
export default {
  name: 'progress-textInside-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style lang="scss" scoped>

</style>
