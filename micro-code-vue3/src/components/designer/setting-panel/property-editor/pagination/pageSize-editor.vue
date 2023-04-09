<template>
  <el-form-item label="每页条数" prop="pageSize">
    <el-select v-model="optionModel.pageSize" style="width: 100%">
      <el-option v-for="item in optionModel.pageSizes" :key="item.pageSize" :label="item.pageSize"
                 :value="item.pageSize"
      ></el-option>
    </el-select>
  </el-form-item>
</template>

<script>
export default {
  name: 'pageSize-editor',
  props: {
    designer: Object,
    selectedWidget: Object,
    optionModel: Object
  }
}
</script>

<style scoped>

</style>
