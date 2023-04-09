<template>
  <el-form-item label="默认值">
    <el-input v-model="optionModel.defaultValue" type="text"></el-input>
  </el-form-item>
</template>
<script>
export default {
  name: 'defaultValue-editor',
  props: {
    optionModel: Object
  }
}
</script>
