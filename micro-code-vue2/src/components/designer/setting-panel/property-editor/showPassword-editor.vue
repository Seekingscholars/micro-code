<template>
  <el-form-item v-if="optionModel.type === 'password'" label="可显示密码">
    <el-switch v-model="optionModel.showPassword"></el-switch>
  </el-form-item>
</template>

<script>


export default {
  name: 'showPassword-editor',

  props: {
    designer: Object,
    selectedWidget: Object,
    optionModel: Object
  }

}
</script>

<style scoped>

</style>
