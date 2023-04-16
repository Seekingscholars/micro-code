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
    <el-dialog
      :before-close="handleClose"
      :fullscreen="field.options.fullscreen"
      :modal="field.options.modal"
      :show-close="field.options.showClose"
      :title="field.options.label"
      :visible.sync="field.options.visible"
      :width="width"
      destroy-on-close
      @closed="onClosed"
      @opened="onOpened"
    >
      <RenderWidget :designer="designer" :widget="field" />
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import RenderWidget from '../RenderWidget'

export default {
  name: 'dialog-widget',
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
      dialogVisible: true
    }
  },
  computed: {
    width() {
      const width = this.field.options.width
      if (width) {
        return width + '%'
      }
    }
  },
  mounted() {
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
</style>
