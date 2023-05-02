<template>
  <el-input-number
    v-model="value"
    class="full-width-input"
    v-bind="field.options"
    v-on="getListeners(field)"
  />
</template>

<script>
import fieldMixin from './fieldMixin'

export default {
  name: 'number-widget',
  componentName: 'FieldWidget',
  mixins: [fieldMixin],
  computed: {
    value: {
      get() {
        return this.dataModel[this.field.options.name]
      },
      set(newValue) {
        if (!newValue || isNaN(newValue)) {
          this.dataModel[this.field.options.name] = null
        } else {
          this.dataModel[this.field.options.name] = Number(newValue)
        }
      }
    }
  }
}
</script>
<style lang="scss" scoped>
.full-width-input {
  width: 100% !important;
}
</style>
