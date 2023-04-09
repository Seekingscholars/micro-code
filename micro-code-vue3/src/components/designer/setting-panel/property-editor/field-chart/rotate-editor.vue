<template>
  <el-form-item label="旋转角度">
    <el-input v-model="optionModel.rotate" type="number"></el-input>
  </el-form-item>
</template>

<script>
export default {
  name: 'rotate-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style lang="scss" scoped>

</style>
