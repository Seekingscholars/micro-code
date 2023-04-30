<template>
  <el-form-item label="水平排列">
    <el-select v-model="optionModel.type" style="width: 100%">
      <el-option v-for="item in dataList" :key="item.value" :label="item.label"
                 :value="item.value"
      >
      </el-option>
    </el-select>
  </el-form-item>
</template>

<script>
export default {
  name: 'grid-type-editor',
  props: {
    optionModel: Object
  },
  data() {
    return {
      dataList: [
        {label: 'default', value: ''},
        {label: 'flex', value: 'flex'}
      ]
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
