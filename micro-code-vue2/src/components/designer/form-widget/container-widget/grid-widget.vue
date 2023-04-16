<template>
  <el-row :class="[designer.design?'grid-container':'']"
          v-bind="field.options"
  >
    <RenderWidget :designer="designer" :widget="field"></RenderWidget>
  </el-row>
</template>

<script>
import RenderWidget from '../RenderWidget'

export default {
  name: 'grid-widget',
  componentName: 'GridWidget',
  components: {
    RenderWidget
  },
  props: {
    field: Object,
    designer: Object
  }
}
</script>

<style lang="scss" scoped>

</style>
