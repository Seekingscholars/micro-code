<template>
  <el-form-item label="flex布局模式">
    <el-select v-model="optionModel.justify">
      <el-option v-for="item in dataList" :key="item.value" :label="item.label"
                 :value="item.value"
      >
      </el-option>
    </el-select>
  </el-form-item>
</template>

<script>
export default {
  name: 'justify-editor',
  props: {
    optionModel: Object
  },
  data() {
    return {
      dataList: [
        { label: 'start', value: 'start' },
        { label: 'end', value: 'end' },
        { label: 'center', value: 'center' },
        { label: 'space-around', value: 'space-around' },
        { label: 'space-between', value: 'space-between' }
      ]
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
