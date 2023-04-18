<template>
  <div :style="!!field.options.fontSize ? `font-size: ${field.options.fontSize};`: ''">
    {{ field.options.textContent }}
  </div>
</template>

<script>
import fieldMixin from './fieldMixin'

export default {
  name: 'static-text-widget',
  componentName: 'FieldWidget',
  mixins: [fieldMixin],
  props: {
    field: Object,
    parentWidget: Object,
    parentList: Array,
    indexOfParentList: Number,
    designer: Object

  },

  created() {

  }

}
</script>

<style lang="scss" scoped>


</style>
