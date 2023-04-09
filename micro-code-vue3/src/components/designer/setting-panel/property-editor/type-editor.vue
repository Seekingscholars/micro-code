<template>
  <el-form-item label="显示类型">
    <el-select v-model="optionModel.type">
      <el-option label="text" value="text" />
      <el-option label="password" value="password" />
    </el-select>
  </el-form-item>
</template>

<script>
export default {
  name: "TypeEditor",
  props: {
    optionModel: Object
  },
  computed: {}
};
</script>

<style lang="scss" scoped></style>
