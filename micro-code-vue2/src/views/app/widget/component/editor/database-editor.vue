<template>
  <el-form-item label="数据库">
    <el-input v-model="optionModel.database"></el-input>
  </el-form-item>
</template>

<script>
export default {
  name: 'database-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style lang="scss" scoped>

</style>
