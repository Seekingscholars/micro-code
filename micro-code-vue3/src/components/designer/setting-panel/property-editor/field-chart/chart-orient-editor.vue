<template>
  <el-form-item label="布局方向" prop="orient">
    <el-select v-model="optionModel.orient" style="width: 100%">
      <el-option v-for="item in orientList" :key="item.value" :label="item.label" :value="item.value"
      ></el-option>
    </el-select>
  </el-form-item>
</template>
<script>
export default {
  name: 'chart-orient-editor',
  props: {
    optionModel: Object
  },
  data() {
    return {
      orientList: [
        { label: '横向', value: 'horizontal' },
        { label: '纵向', value: 'vertical' }
      ]
    }
  }
}
</script>

<style scoped>

</style>
