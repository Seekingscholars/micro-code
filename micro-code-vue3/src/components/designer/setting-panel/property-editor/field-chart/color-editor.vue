<template>
  <el-form-item label="颜色">
    <el-input v-model="optionModel.color" type="color"></el-input>
  </el-form-item>
</template>
<script>
export default {
  name: "color-editor",
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
