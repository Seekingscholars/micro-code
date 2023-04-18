<template>
  <el-form-item label="选项卡类型">
    <el-select v-model="optionModel.type">
      <el-option v-for="item in dataList" :key="item.value" :label="item.label"
                 :value="item.value"
      >
      </el-option>
    </el-select>
  </el-form-item>
</template>

<script>
export default {
  name: 'tab-type-editor',
  props: {
    optionModel: Object
  },
  data() {
    return {
      dataList: [
        {label: 'border-card', value: 'border-card'},
        {label: 'card', value: 'card'}
      ]
    }
  }
}
</script>

<style scoped>

</style>
