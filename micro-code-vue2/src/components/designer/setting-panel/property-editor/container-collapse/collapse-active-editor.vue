<template>
  <el-form-item label="激活折叠面板">
    <el-select v-model="optionModel.active" multiple style="width: 100%">
      <el-option v-for="item in dataList" :key="item.value" :label="item.label"
                 :value="item.value"
      >
      </el-option>
    </el-select>
  </el-form-item>
</template>

<script>
export default {
  name: 'collapse-active-editor',
  props: {
    optionModel: Object
  },
  data() {
    return {
      dataList: []
    }
  },
  watch: {
    'optionModel.tabs'(newValue) {
      this.changeDataList(newValue)
    }
  },
  mounted() {
    this.changeDataList(this.optionModel.tabs)
  },
  methods: {
    changeDataList(dataList) {
      if (dataList) {
        this.dataList = dataList.map(tab => {
          return {
            label: tab.label,
            value: tab.value
          }
        })
      } else {
        this.dataList = []
      }
    }
  }
}
</script>

<style scoped>

</style>
