<template>
  <el-form-item label="高度">
    <el-input v-model="optionModel.height" type="number"></el-input>
  </el-form-item>
</template>

<script>
export default {
  name: 'height-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style lang="scss" scoped>

</style>
