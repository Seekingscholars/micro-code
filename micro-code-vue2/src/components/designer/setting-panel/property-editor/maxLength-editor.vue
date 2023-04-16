<template>
  <el-form-item label="最大长度">
    <el-input v-model="maxLength" class="hide-spin-button" min="1"
              style="width: 100%" type="number" @input.native="inputNumberHandler"
    ></el-input>
  </el-form-item>
</template>

<script>

import propertyMixin from './propertyMixin'

export default {
  name: 'maxLength-editor',
  mixins: [propertyMixin],
  props: {
    optionModel: Object
  },
  computed: {
    maxLength: {
      get() {
        return this.optionModel['maxLength']
      },

      set(newValue) {
        if (!newValue || isNaN(newValue)) {
          this.optionModel.maxLength = null
        } else {
          this.optionModel.maxLength = Number(newValue)
        }
      }
    }
  }
}
</script>

<style scoped>

</style>
