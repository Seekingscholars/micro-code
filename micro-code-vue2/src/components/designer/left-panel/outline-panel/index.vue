<template>
  <el-tree :data="designer.widgetList" :props="defaultProps" draggable node-key="id" :expand-on-click-node="false" :allow-drop="allowDrop" default-expand-all @node-click="handleNodeClick">
    <div slot-scope="{ node, data }">
      <div class="node-label">{{ getLabel(data) }}</div>
    </div>
  </el-tree>
</template>

<script>
export default {
  name: 'OutlinePanel',
  props: {
    designer: Object
  },
  data() {
    return {
      defaultProps: {
        label: 'id',
        children: 'widgetList'
      }
    }
  },
  created() {
  },
  methods: {
    allowDrop(draggingNode, dropNode, type) {
      if (dropNode.data.widgetList) {
        return true
      } else {
        return false
      }
    },
    getLabel(widget) {
      if (!widget) {
        return
      }
      let label
      if (widget['form-item']) {
        label = widget['form-item'].label
        if (label) {
          return label
        }
      }
      if (widget.options) {
        label = widget.options.label
        if (label) {
          return label
        }
      }
      label = widget.name
      return label
    },
    handleNodeClick(node) {
      const foundW = this.designer.findWidgetById(node.id)
      if (foundW) {
        this.designer.setSelected(foundW)
      }
    }
  }
}
</script>

<style scoped>
</style>
