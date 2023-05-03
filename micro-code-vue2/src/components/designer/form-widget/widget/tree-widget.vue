<template>
  <el-tree
    ref="tree"
    :data="dataList"
    node-key="id"
    v-bind="field.options"
  >
  </el-tree>
</template>

<script>

import fieldMixin from './fieldMixin'
import{ wrapWith } from '../../designer'
export default {
  name: 'tree-widget',
  componentName: 'FieldWidget',
  mixins: [fieldMixin],
  computed: {
    dataList() {
      const data = this.field.options.data
      let dataList
      if (!!data) {
        const customFn = new Function(wrapWith('return ' + data))
        dataList = customFn.call(this)
      }
      return dataList || []
    }
  }
}
</script>

<style lang="scss" scoped>


</style>
