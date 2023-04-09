<template>
  <el-form-item label="名称" prop="name">
    <el-input v-model="optionModel.name" type="text"></el-input>
  </el-form-item>
</template>

<script>
export default {
  name: 'name-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style lang="scss" scoped>

</style>
