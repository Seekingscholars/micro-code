<template>
  <el-form-item label="水平方向">
    <el-select v-model="optionModel.left" style="width: 100%" filterable allow-create>
      <el-option v-for="item in leftList" :key="item.value" :label="item.label" :value="item.value"
      ></el-option>
    </el-select>
  </el-form-item>
</template>
<script>
export default {
  name: 'chart-left-editor',
  props: {
    optionModel: Object
  },
  data() {
    return {
      leftList: [
        { label: '居左', value: 'left' },
        { label: '居右', value: 'right' },
        { label: '居中', value: 'center' }
    ]
    }
  }
}
</script>

<style scoped>

</style>
