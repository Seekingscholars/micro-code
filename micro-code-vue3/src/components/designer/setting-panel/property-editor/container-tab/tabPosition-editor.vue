<template>
  <el-form-item label="选项卡位置">
    <el-select v-model="optionModel.tabPosition">
      <el-option v-for="item in dataList" :key="item.value" :label="item.label"
                 :value="item.value"
      >
      </el-option>
    </el-select>
  </el-form-item>
</template>

<script>
export default {
  name: 'tabPosition-editor',
  props: {
    optionModel: Object
  },
  data() {
    return {
      dataList: [
        {label: 'top', value: 'top'},
        {label: 'right', value: 'right'},
        {label: 'bottom', value: 'bottom'},
        {label: 'left', value: 'left'}
      ]
    }
  }
}
</script>

<style scoped>

</style>
