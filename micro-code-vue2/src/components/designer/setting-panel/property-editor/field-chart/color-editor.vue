<template>
  <el-form-item label="颜色">
    <el-input type="color" v-model="optionModel.color"></el-input>
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
