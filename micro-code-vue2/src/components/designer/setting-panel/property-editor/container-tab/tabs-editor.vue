<template>
  <div>
    <el-table
      v-sort="dragOption"
      :data="designer.selectedWidget.widgetList"
      border
      row-key="value"
    >
      <el-table-column
        label="标签"
        prop="name"
      >
        <template slot-scope="scope">
          <el-input v-model="scope.row.name" />
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        width="60px"
      >
        <template slot-scope="scope">
          <div class="toolbar">
            <div
              class="drag-handler toolbar-button"
              style="cursor: move"
            >
              <i
                class="el-icon-sort"
                title="排序"
              />
            </div>
            <div
              class="toolbar-button"
              @click="deleteTabPane(scope.row)"
            >
              <i
                class="el-icon-delete"
                style="color: red;cursor: pointer"
                title="删除"
              />
            </div>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <el-button
      type="text"
      @click="addTabPane"
    >添加一项+</el-button>
  </div>
</template>
<script>
import { deepClone } from '@/utils/util'
import { option } from '@/utils/dragOption'

export default {
  name: 'tabs-editor',
  componentName: 'PropertyEditor',
  props: {
    optionModel: Object,
    designer: Object
  },
  computed: {
    dragOption() {
      return option(this.designer.selectedWidget.widgetList)
    }
  },
  methods: {
    addTabPane() {
      const newTab = deepClone(this.designer.selectedWidget.widgetList[0])
      newTab.value = 'tab' + (this.designer.selectedWidget.widgetList.length + 1)
      newTab.name = '标签' + (this.designer.selectedWidget.widgetList.length + 1)
      this.designer.selectedWidget.widgetList.push(newTab)
    },
    deleteTabPane(row) {
      if (this.designer.selectedWidget.widgetList.length === 1) {
        this.$message.info('仅剩一个选项卡页不可删除')
        return
      }
      this.$modal.confirm('是否确认删除？').then(() => {
        for (let i = 0; i < this.designer.selectedWidget.widgetList.length; i++) {
          if (this.designer.selectedWidget.widgetList[i].value === row.value) {
            this.designer.selectedWidget.widgetList.splice(i, 1)
            break
          }
        }
      })
    }
  }
}
</script>
<style lang="scss" scoped>
.toolbar {
  display: flex;
}
.toolbar-button {
  margin-right: 20px;
}

</style>
