<template>
  <el-form-item label="标签">
    <el-input v-model="optionModel.label" type="text"></el-input>
  </el-form-item>
</template>

<script>
export default {
  name: 'label-editor',
  props: {
    designer: Object,
    selectedWidget: Object,
    optionModel: Object
  }
}
</script>

<style lang="scss" scoped>

</style>
