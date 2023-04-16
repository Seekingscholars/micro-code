<template>
  <el-form-item label="最小长度">
    <el-input v-model="minLength" class="hide-spin-button" min="0"
              style="width: 100%" type="number" @input.native="inputNumberHandler"
    ></el-input>
  </el-form-item>
</template>

<script>
import propertyMixin from './propertyMixin'

export default {
  name: 'minLength-editor',
  mixins: [propertyMixin],
  props: {
    designer: Object,
    selectedWidget: Object,
    optionModel: Object
  },
  computed: {
    minLength: {
      get() {
        return this.optionModel['minLength']
      },

      set(newValue) {
        if (!newValue || isNaN(newValue)) {
          this.optionModel.minLength = null
        } else {
          this.optionModel.minLength = Number(newValue)
        }
      }
    }

  }
}
</script>

<style scoped>

</style>
