<template>
  <div>
    <div
      v-if="designer.design"
      style="outline: 1px dashed #cb2f2f;
  height: 80px;
  text-align: center;
  line-height: 80px;
  font-weight: bold;
  color: #cb2f2f;"
    >
      {{ field.options.label }}
    </div>
    <el-drawer
      :modal="false"
      :visible.sync="drawer"
      :wrapper-closable="false"
      title="我是标题"
      v-bind="field.options"
    >
      <RenderWidget :designer="designer" :widget="field"/>
    </el-drawer>
  </div>
</template>
<script>
import RenderWidget from '../RenderWidget'

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
