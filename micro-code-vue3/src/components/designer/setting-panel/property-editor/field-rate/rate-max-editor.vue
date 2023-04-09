<template>
  <el-form-item label="最大评分值">
    <el-input-number v-model="optionModel.max" :max="10" :min="1"
                     class="hide-spin-button" style="width: 100%"
    ></el-input-number>
  </el-form-item>
</template>

<script>
export default {
  name: 'rate-max-editor',

  props: {
    optionModel: Object
  }

}
</script>

<style scoped>

</style>
