<template>
  <el-form-item label="阴影">
    <el-select v-model="optionModel.shadow">
      <el-option v-for="item in dataList" :key="item.value" :label="item.label"
                 :value="item.value"
      >
      </el-option>
    </el-select>
  </el-form-item>
</template>

<script>
export default {
  name: 'card-shadow-editor',
  props: {
    optionModel: Object
  },
  data() {
    return {
      dataList: [
        { label: 'always', value: 'always' },
        { label: 'hover', value: 'hover' },
        { label: 'never', value: 'never' }
      ]
    }
  }
}
</script>

<style scoped>

</style>
