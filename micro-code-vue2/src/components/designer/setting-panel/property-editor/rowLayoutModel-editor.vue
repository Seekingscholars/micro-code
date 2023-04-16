<template>
  <el-form-item label="布局模式">
    <el-input-number v-model.number="optionModel.span" :max="24" :min="1"
                     style="width: 100%"
    ></el-input-number>
  </el-form-item>
</template>

<script>

export default {
  name: 'rowLayoutModel-editor',
  props: {
    designer: Object,
    selectedWidget: Object,
    optionModel: Object
  }
}
</script>

<style lang="scss" scoped>

</style>
