<template>
  <el-form-item label="显示类型">
    <el-select v-model="optionModel.type" style="width: 100%">
      <el-option label="text" value="text"></el-option>
      <el-option label="password" value="password"></el-option>
    </el-select>
  </el-form-item>
</template>

<script>
export default {
  name: 'input-type-editor',
  props: {
    optionModel: Object
  },
  computed: {}
}
</script>

<style lang="scss" scoped>

</style>
