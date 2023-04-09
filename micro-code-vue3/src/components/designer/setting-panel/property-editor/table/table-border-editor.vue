<template>
  <el-form-item label="边框" prop="border">
    <el-switch v-model="optionModel.border"/>
  </el-form-item>
</template>

<script>
export default {
  name: 'table-border-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
