<template>
  <el-form-item label="i开关宽度">
    <el-input-number v-model="optionModel.switchWidth" style="width: 100%"></el-input-number>
  </el-form-item>
</template>

<script>


export default {
  name: 'switchWidth-editor',

  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
