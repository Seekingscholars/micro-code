<template>
  <el-checkbox-group v-model="dataModel[field.options.name]"
                     :disabled="field.options.disabled" :size="field.options.size"
                     v-on="getListeners(field)"
  >
    <template v-if="!!field.options.buttonStyle">
      <el-checkbox-button v-for="(item, index) in dataList" :key="index" :border="field.options.border"
                          :disabled="item.disabled" :label="item.value"
      >{{ item.label }}
      </el-checkbox-button>
    </template>
    <template v-else>
      <el-checkbox v-for="(item, index) in dataList" :key="index" :border="field.options.border"
                   :disabled="item.disabled" :label="item.value"
      >{{ item.label }}
      </el-checkbox>
    </template>
  </el-checkbox-group>
</template>

<script>
import fieldMixin from './fieldMixin'

export default {
  name: 'checkbox-widget',
  componentName: 'FieldWidget',
  mixins: [fieldMixin],
  props: {
    field: Object
  },
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


</style>
