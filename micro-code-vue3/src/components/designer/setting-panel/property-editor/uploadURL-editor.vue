<template>
  <div>
    <el-form-item label-width="0">
      <el-divider class="custom-divider">上传参数设置</el-divider>
    </el-form-item>
    <el-form-item label="上传地址">
      <el-input v-model="optionModel.uploadURL" type="text"></el-input>
    </el-form-item>
  </div>
</template>

<script>


export default {
  name: 'uploadURL-editor',

  props: {
    designer: Object,
    selectedWidget: Object,
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
