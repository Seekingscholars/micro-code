<template>
  <el-form-item label="朴素按钮">
    <el-switch v-model="optionModel.plain"></el-switch>
  </el-form-item>
</template>

<script>


export default {
  name: 'plain-editor',

  props: {
    optionModel: Object
  }

}
</script>

<style scoped>

</style>
