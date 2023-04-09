<template>
  <el-form-item label="可远程搜索">
    <el-switch v-model="optionModel.remote" @change="onRemoteChange"></el-switch>
  </el-form-item>
</template>

<script>

import propertyMixin from './propertyMixin'

export default {
  name: 'remote-editor',
  mixins: [propertyMixin],
  props: {
    designer: Object,
    selectedWidget: Object,
    optionModel: Object
  }

}
</script>

<style scoped>

</style>
