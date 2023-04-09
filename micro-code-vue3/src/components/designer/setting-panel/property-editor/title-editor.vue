<template>
  <el-form-item label="标题">
    <el-input v-model="optionModel.title"></el-input>
  </el-form-item>
</template>

<script>
export default {
  name: 'title-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style lang="scss" scoped>

</style>
