<template>
  <el-card :shadow="field.options.shadow">
    <RenderWidget :designer="designer" :widget="field"></RenderWidget>
  </el-card>
</template>

<script>
import RenderWidget from '../RenderWidget.vue'

export default {
  name: 'card-widget',
  componentName: 'ContainerWidget',
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
