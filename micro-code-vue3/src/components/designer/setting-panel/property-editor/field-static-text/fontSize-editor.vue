<template>
  <el-form-item label="字体大小">
    <el-input v-model="optionModel.fontSize"></el-input>
  </el-form-item>
</template>

<script>
export default {
  name: 'fontSize-editor',

  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
