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

export default {
  name: 'tree-widget',
  componentName: 'FieldWidget',
  mixins: [fieldMixin],
  computed: {
    dataList() {
      const data = this.field.options.data
      let dataList
      if (!!data) {
        let customFn = new Function(this.wrapWith('return ' + data))
        dataList = customFn.call(this)
      }
      return dataList || []
    }
  }
}
</script>

<style lang="scss" scoped>


</style>
