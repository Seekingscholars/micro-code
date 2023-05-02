<template>
  <el-form-item label="激活折叠面板">
    <el-select v-model="optionModel.active" multiple style="width: 100%">
      <el-option v-for="item in widgetList" :key="item.value" :label="item.label"
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
    selectedWidget: Object,
    optionModel: Object
  },
  computed: {
    widgetList() {
      debugger
      const dataList = this.selectedWidget.widgetList
      if (dataList) {
        return dataList.map(tab => {
          return {
            label: tab.name,
            value: tab.value
          }
        })
      } else {
        return []
      }
    }
  }
}
</script>

<style scoped>

</style>
