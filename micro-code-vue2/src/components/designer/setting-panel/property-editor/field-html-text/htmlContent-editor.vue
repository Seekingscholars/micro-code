<template>
  <div>
    <el-form-item label="HTML">
    </el-form-item>
    <el-form-item label-width="0">
      <el-input v-model="optionModel.htmlContent" :rows="5" class="html-content-editor" type="textarea"></el-input>
    </el-form-item>
  </div>
</template>

<script>


export default {
  name: 'htmlContent-editor',

  props: {
    designer: Object,
    selectedWidget: Object,
    optionModel: Object
  }
}
</script>

<style scoped>
.html-content-editor {
  font-size: 13px;
}
</style>
