<template>
  <el-form-item label="字段组">
    <el-input v-model="optionModel.group"></el-input>
  </el-form-item>
</template>

<script>

export default {
  name: 'group-editor',
  props: {
    designer: Object,
    selectedWidget: Object,
    optionModel: Object
  },
  created() {

  }
}
</script>

<style scoped>

</style>
