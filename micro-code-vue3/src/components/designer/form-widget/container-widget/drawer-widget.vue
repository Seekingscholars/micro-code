<template>
  <el-drawer
    :modal="false"
    :visible.sync="drawer"
    :wrapperClosable="false"
    title="我是标题"
    v-bind="field.options"
  >
    <RenderWidget :designer="designer" :widget="field"></RenderWidget>
  </el-drawer>
</template>
<script>
import RenderWidget from '../RenderWidget.vue'

export default {
  name: 'drawer-widget',
  componentName: 'ContainerWidget',
  components: {
    RenderWidget
  },
  props: {
    field: Object,
    designer: Object
  },
  data() {
    return {
      drawer: true
    }
  },
  mounted() {
    this.designer.selectedPanel = this.field.id
  },
  methods: {
    onOpened() {

    },
    onClosed() {
      this.designer.selectedPanel = null
    },
    handleClose(done) {
      done()
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
