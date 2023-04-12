<template>
  <div @click.stop="selectWidget(field)">
    <el-dialog
      v-model="field.options.visible"
      :before-close="handleClose"
      :fullscreen="field.options.fullscreen"
      :modal="field.options.modal"
      :showClose="field.options.showClose"
      :title="field.options.label"
      :width="width"
      @closed="onClosed"
      @opened="onOpened"
    >
      <RenderWidget :designer="designer" :widget="field"></RenderWidget>
      <template #footer>
      <span class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
  </span>
      </template>
    </el-dialog>
  </div>
</template>
<script>
import RenderWidget from '../RenderWidget.vue'

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
