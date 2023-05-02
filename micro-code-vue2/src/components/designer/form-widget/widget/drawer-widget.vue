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
      {{ field.options.title }}
    </div>
    <el-drawer
      :before-close="handleClose"
      :direction="field.options.direction"
      :fullscreen="field.options.fullscreen"
      :modal="field.options.modal"
      :withHeader="field.options.withHeader"
      :show-close="field.options.showClose"
      :wrapperClosable="false"
      :title="field.options.title"
      :visible.sync="field.options.visible"
      :size="field.options.width"
      destroy-on-close
      @closed="onClosed"
      @opened="onOpened"
    >
      <RenderWidget :designer="designer" :widget="field"/>
      <div class="drawer-footer">
        <el-button @click="field.options.visible = false">取 消</el-button>
        <el-button type="primary" @click="field.options.visible = false">确 定</el-button>
      </div>
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
  beforeDestroy() {
    this.onClosed()
  },
  methods: {
    selectWidget(widget) {
      this.designer.setSelected(widget)
    },
    onOpened() {
      this.designer.selectedPanel = this.field.id
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
.drawer-footer{
  position: absolute;
  right: 10px;
  bottom: 10px;
}
</style>
