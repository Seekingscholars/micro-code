<template>
  <el-form-item label="类型" prop="type">
    <el-select v-model="optionModel.type" style="width: 100%">
      <el-option v-for="item in typeList" :key="item.value" :label="item.label" :value="item.value"
      ></el-option>
    </el-select>
  </el-form-item>
</template>
<script>
export default {
  name: 'chart-type-editor',
  props: {
    optionModel: Object
  },
  data() {
    return {
      typeList: [
        {label: '饼图', value: 'pie'},
        {label: '仪表盘', value: 'gauge'},
        {label: '漏斗图', value: 'funnel'}
      ]
    }
  }
}
</script>

<style scoped>

</style>
