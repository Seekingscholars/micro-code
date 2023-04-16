<template>
  <el-form-item label="栅格间隔">
    <el-input-number v-model="optionModel.gutter" style="width: 100%"></el-input-number>
  </el-form-item>
</template>

<script>
export default {
  name: 'gutter-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style lang="scss" scoped>

</style>
