<template>
  <el-form-item label="onBlur" label-width="120px">
    <el-button icon="el-icon-edit" plain round type="info" @click="editEventHandler('blur', eventParams)">
      编写代码
    </el-button>
  </el-form-item>
</template>

<script>
import eventMixin from '../event-handler/eventMixin'

export default {
  name: 'blur-editor',
  mixins: [eventMixin],
  props: {
    designer: Object,
    selectedWidget: Object,
    optionModel: Object
  },
  data() {
    return {
      eventParams: ['event']
    }
  }
}
</script>

<style scoped>

</style>
