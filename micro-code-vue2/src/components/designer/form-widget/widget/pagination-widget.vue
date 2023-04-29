<template>
  <el-pagination :current-page="currentPage" :page-count="pageCount" :total="total" v-bind="field.options">
  </el-pagination>
</template>

<script>

import fieldMixin from './fieldMixin'

export default {
  name: 'pagination-widget',
  componentName: 'FieldWidget',
  mixins: [fieldMixin],
  data() {
    return {
      total: 0,
      currentPage: 1,
      pageCount: 0
    }
  }
}
</script>

<style lang="scss" scoped>


</style>
