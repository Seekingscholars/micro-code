<template>
  <el-form-item label="维度">
    <el-input v-model="optionModel.dimension"></el-input>
  </el-form-item>
</template>

<script>
export default {
  name: 'dimension-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
