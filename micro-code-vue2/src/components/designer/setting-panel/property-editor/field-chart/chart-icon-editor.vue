<template>
  <el-form-item label="图标" prop="icon">
    <el-select v-model="optionModel.icon" style="width: 100%">
      <el-option v-for="item in iconList" :key="item.value" :label="item.label" :value="item.value"
      ></el-option>
    </el-select>
  </el-form-item>
</template>
<script>
export default {
  name: 'chart-icon-editor',
  props: {
    optionModel: Object
  },
  data() {
    return {
      iconList: [
        { label: '矩形', value: 'rect' },
        { label: '圆角', value: 'roundRect' },
        { label: '圆', value: 'circle' },
        { label: '三角形', value: 'triangle' },
        { label: '菱形', value: 'diamond' },
        { label: '无', value: 'none' }
      ]
    }
  }
}
</script>

<style scoped>

</style>
