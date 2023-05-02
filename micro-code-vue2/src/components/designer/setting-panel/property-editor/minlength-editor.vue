<template>
  <el-form-item label="最小长度">
    <el-input v-model="optionModel.minlength" class="hide-spin-button" min="0" type="number"
    ></el-input>
  </el-form-item>
</template>

<script>
import propertyMixin from './propertyMixin'

export default {
  name: 'minlength-editor',
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
