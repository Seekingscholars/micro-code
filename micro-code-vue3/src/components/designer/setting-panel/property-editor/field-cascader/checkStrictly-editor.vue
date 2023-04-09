<template>
  <el-form-item label="任意级节点可选">
    <el-switch v-model="optionModel.checkStrictly"></el-switch>
  </el-form-item>
</template>

<script>


export default {
  name: 'checkStrictly-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
