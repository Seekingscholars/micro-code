<template>
  <el-form-item label="onUploadError" label-width="120px">
    <el-button icon="el-icon-edit" plain round type="info" @click="editEventHandler('onUploadError', eventParams)">
      编写代码
    </el-button>
  </el-form-item>
</template>

<script>

import eventMixin from '../event-handler/eventMixin'

export default {
  name: 'onUploadError-editor',
  mixins: [eventMixin],
  props: {
    designer: Object,
    selectedWidget: Object,
    optionModel: Object
  },
  data() {
    return {
      eventParams: ['error', 'file', 'fileList']
    }
  }
}
</script>

<style scoped>

</style>
