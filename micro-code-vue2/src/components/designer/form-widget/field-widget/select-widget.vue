<template>
  <el-select v-model="dataModel[field.options.name]"
             class="full-width-input" v-bind="field.options" v-on="getListeners(field)"
  >
    <el-option v-for="item in dataList" :key="item.value" :disabled="item.disabled"
               :label="item.label" :value="item.value"
    >
    </el-option>
  </el-select>
</template>

<script>
import fieldMixin from './fieldMixin'

export default {
  name: 'select-widget',
  componentName: 'FieldWidget',
  mixins: [fieldMixin],
  computed: {
    dataList() {
      const optionItem = this.field['option-item']
      const data = optionItem.data
      const items = optionItem.items
      let dataList
      if (!!data) {
        let customFn = new Function(this.wrapWith('return ' + data))
        dataList = customFn.call(this)
      } else if (items) {
        dataList = items
      }
      return dataList || []
    }
  }
}
</script>

<style lang="scss" scoped>
.full-width-input {
  width: 100% !important;
}

</style>
