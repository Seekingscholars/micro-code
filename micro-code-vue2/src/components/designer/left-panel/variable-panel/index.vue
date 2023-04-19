<template>
  <el-tree
    :data="dataList"
    :default-expanded-keys="defaultExpandedKeys"
    node-key="id"
    @node-expand="handleNodeExpand"
    @node-collapse="handleNodeCollapse"
  >
    <div slot-scope="{ node, data }">
      <div class="flex">
        <div class="label">{{ data.label }}</div>
        <div :class="{value:true,content:!data.type}">{{ data.value }}</div>
        <div v-if="data.children" class="count">{{ data.children.length }}项</div>
      </div>
    </div>
  </el-tree>
</template>

<script>
export default {
  name: 'VariablePanel',
  inject: ['$model'],
  data() {
    return {
      dataList: [],
      defaultExpandedKeys: [0],
      index: null
    }
  },
  methods: {
    refresh() {
      this.index = 1
      this.dataList = [{ id: 0, label: '全局变量', ...this.loadNode(this.$model) }]
    },
    loadNode(value) {
      if (value === null) {
        return { value: null }
      } else if (typeof value === 'function') {
        return { value: 'function', type: 'function' }
      } else if (Array.isArray(value)) {
        const treeArray = []
        for (let i = 0; i < value.length; i++) {
          treeArray.push({ id: this.index++, label: '[' + i + ']', ...this.loadNode(value[i]) })
        }
        return { value: '[]', type: 'array', children: treeArray }
      } else if (typeof value === 'object') {
        const treeArray = []
        Object.keys(value).forEach(key => {
          treeArray.push({ id: this.index++, label: key, ...this.loadNode(value[key]) })
        })
        return { value: '{}', type: 'object', children: treeArray }
      } else {
        return { value: value }
      }
    },
    handleNodeExpand(data) {
      this.defaultExpandedKeys.push(data.id)
    },
    handleNodeCollapse(data) {
      for (let i = this.defaultExpandedKeys.length - 1; i >= 0; i--) {
        if (this.defaultExpandedKeys[i] === data.id) {
          this.defaultExpandedKeys.splice(i, 1)
          break
        }
      }
    }
  }
}
</script>

<style scoped>
.flex {
  display: flex;
  font-size: 14px;
}

.label {
  font-weight: bold;
}

.value {
  margin-left: 10px;
}

.content {
  color: #FF9816;
}

.count {
  margin-left: 10px;
}
</style>
