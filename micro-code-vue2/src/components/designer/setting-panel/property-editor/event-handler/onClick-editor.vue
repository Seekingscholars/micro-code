<template>
  <el-form-item label="onClick" label-width="120px">
    <el-button icon="el-icon-edit" plain round type="info" @click="editEventHandler('click', eventParams)">
      编写代码
    </el-button>
  </el-form-item>
</template>

<script>

import eventMixin from '../event-handler/eventMixin'

export default {
  name: 'click-editor',
  mixins: [eventMixin],
  props: {
    designer: Object,
    selectedWidget: Object,
    optionModel: Object
  },
  data() {
    return {
      eventParams: []
    }
  }
}
</script>

<style scoped>

</style>
