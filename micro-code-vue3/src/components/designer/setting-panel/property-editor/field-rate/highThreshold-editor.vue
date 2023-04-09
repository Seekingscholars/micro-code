<template>
  <el-form-item label="高分界限值">
    <el-input-number v-model="optionModel.highThreshold" :max="optionModel.max" :min="optionModel.lowThreshold"
                     class="hide-spin-button" style="width: 100%"
    ></el-input-number>
  </el-form-item>
</template>

<script>
export default {
  name: 'highThreshold-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
