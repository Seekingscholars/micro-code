<template>
  <el-form-item label="宽度">
    <el-input v-model="optionModel.width" style="width: 100%" type="number"></el-input>
  </el-form-item>
</template>

<script>
export default {
  name: 'width-editor',
  props: {
    designer: Object,
    selectedWidget: Object,
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
