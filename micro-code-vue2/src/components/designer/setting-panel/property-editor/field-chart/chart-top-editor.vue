<template>
  <el-form-item label="垂直方向">
    <el-select v-model="optionModel.top" allow-create filterable style="width: 100%">
      <el-option v-for="item in topList" :key="item.value" :label="item.label" :value="item.value"
      ></el-option>
    </el-select>
  </el-form-item>
</template>
<script>
export default {
  name: 'chart-top-editor',
  props: {
    optionModel: Object
  },
  data() {
    return {
      topList: [
        {label: '顶部', value: 'top'},
        {label: '居中', value: 'middle'},
        {label: '底部', value: 'bottom'}
      ]
    }
  }
}
</script>

<style scoped>

</style>
