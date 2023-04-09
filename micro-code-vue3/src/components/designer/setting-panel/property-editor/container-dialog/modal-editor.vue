<template>
  <el-form-item label="遮罩">
    <el-switch v-model="optionModel.modal"></el-switch>
  </el-form-item>
</template>

<script>
export default {
  name: 'modal-editor',
  props: {
    designer: Object,
    selectedWidget: Object,
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
