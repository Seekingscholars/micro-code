<template>
  <el-form-item label="校验失败提示">
    <el-input v-model="optionModel.validationHint" type="text"></el-input>
  </el-form-item>
</template>

<script>


export default {
  name: 'validationHint-editor',

  props: {
    designer: Object,
    selectedWidget: Object,
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
