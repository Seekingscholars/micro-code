<template>
  <el-form-item label="是否竖向显示">
    <el-switch v-model="optionModel.vertical"></el-switch>
  </el-form-item>
</template>

<script>
export default {
  name: 'vertical-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
