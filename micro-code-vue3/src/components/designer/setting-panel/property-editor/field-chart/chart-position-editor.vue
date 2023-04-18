<template>
  <el-form-item label="位置" prop="position">
    <el-select v-model="optionModel.position" style="width: 100%">
      <el-option v-for="item in positionList" :key="item.value" :label="item.label" :value="item.value"
      ></el-option>
    </el-select>
  </el-form-item>
</template>
<script>
export default {
  name: 'chart-position-editor',
  props: {
    optionModel: Object
  },
  data() {
    return {
      positionList: [
        {label: '顶部', value: 'top'},
        {label: '左边', value: 'left'},
        {label: '右边', value: 'right'},
        {label: '底部', value: 'bottom'},
        {label: '内侧', value: 'inside'},
        {label: '左内侧', value: 'insideLeft'},
        {label: '右内侧', value: 'insideRight'},
        {label: '内底部', value: 'insideBottom'},
        {label: '内顶部', value: 'insideTop'},
        {label: '左上角内侧', value: 'insideTopLeft'},
        {label: '左下角内侧', value: 'insideBottomLeft'},
        {label: '右上角内侧', value: 'insideTopRight'},
        {label: '右下角内侧', value: 'insideBottomRight'}
      ]
    }
  }
}
</script>

<style scoped>

</style>
