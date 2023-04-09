<template>
  <el-form-item label="发送cookie凭证">
    <el-switch v-model="optionModel.withCredentials"></el-switch>
  </el-form-item>
</template>

<script>


export default {
  name: 'withCredentials-editor',

  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
