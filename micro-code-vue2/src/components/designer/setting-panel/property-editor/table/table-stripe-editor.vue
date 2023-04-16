<template>
  <el-form-item label="斑马纹" prop="stripe">
    <el-switch v-model="optionModel.stripe"/>
  </el-form-item>
</template>

<script>
export default {
  name: 'table-stripe-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
