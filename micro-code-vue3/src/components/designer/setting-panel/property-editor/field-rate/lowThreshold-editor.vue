<template>
  <el-form-item label="低分界限值">
    <el-input-number v-model="optionModel.lowThreshold" :max="optionModel.highThreshold" :min="1"
                     class="hide-spin-button" style="width: 100%"
    ></el-input-number>
  </el-form-item>
</template>

<script>
export default {
  name: 'lowThreshold-editor',
  props: {
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
