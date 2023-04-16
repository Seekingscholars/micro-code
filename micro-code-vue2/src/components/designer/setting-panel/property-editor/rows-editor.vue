<template>
  <el-form-item label="行数">
    <el-input-number v-model="optionModel.rows" style="width: 100%"></el-input-number>
  </el-form-item>
</template>

<script>
export default {
  name: 'rows-editor',

  props: {
    optionModel: Object
  }

}
</script>

<style scoped>

</style>
