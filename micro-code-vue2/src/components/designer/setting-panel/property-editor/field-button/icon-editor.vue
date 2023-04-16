<template>
  <el-form-item label="按钮Icon">
    <el-input v-model="optionModel.icon" type="text"></el-input>
  </el-form-item>
</template>

<script>
export default {
  name: 'icon-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
