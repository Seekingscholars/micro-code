<template>
  <el-radio-group v-model="dataModel[field.options.name]"
                  :disabled="field.options.disabled" :size="field.options.size"
                  v-on="getListeners(field)"
  >
    <template v-if="!!field.options.buttonStyle">
      <el-radio-button v-for="(item, index) in dataList" :key="index" :border="field.options.border"
                       :disabled="item.disabled" :label="item.value"
      >{{ item.label }}
      </el-radio-button>
    </template>
    <template v-else>
      <el-radio v-for="(item, index) in dataList" :key="index" :border="field.options.border"
                :disabled="item.disabled" :label="item.value"
      >{{ item.label }}
      </el-radio>
    </template>
  </el-radio-group>
</template>

<script>
import fieldMixin from './fieldMixin'
import{ wrapWith } from '../../designer'
export default {
  name: 'radio-widget',
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
        const customFn = new Function(wrapWith('return ' + data))
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
