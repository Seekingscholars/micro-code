<template>
  <ElTableDraggable ref="ElTableDraggable" :list="designer.selectedWidget.widgetList" handle=".drag-handler">
    <el-table :data="designer.selectedWidget.widgetList" border row-key="value">
      <el-table-column label="排序" width="50px">
        <i class="drag-handler el-icon-sort" style="cursor: move"/>
      </el-table-column>
      <!--      <el-table-column label="属性" prop="value">-->
      <!--        <template slot-scope="scope">-->
      <!--          <el-input v-model="scope.row.value"></el-input>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
      <el-table-column label="标签" prop="name">
        <template slot-scope="scope">
          <el-input v-model="scope.row.name"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="60px">
        <template slot-scope="scope">
          <div class="operate">
            <i
              class="el-icon-delete"
              style="color: red;cursor: pointer"
              title="删除"
              @click="deleteTabPane(scope.row)"
            />
          </div>
        </template>
      </el-table-column>
    </el-table>
    <el-button type="text" @click="addTabPane">添加一项+</el-button>
  </ElTableDraggable>
</template>
<script>
import {deepClone} from '@/utils/util'
import ElTableDraggable from '../../ElTableDraggable'

export default {
  name: 'tabs-editor',
  componentName: 'PropertyEditor',
  components: {
    ElTableDraggable
  },
  props: {
    optionModel: Object,
    designer: Object
  },
  methods: {
    addTabPane() {
      const newTab = deepClone(this.designer.selectedWidget.widgetList[0])
      newTab.value = 'tab' + (this.designer.selectedWidget.widgetList.length + 1)
      newTab.label = '标签' + (this.designer.selectedWidget.widgetList.length + 1)
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
            this.$nextTick(() => {
              this.$refs.ElTableDraggable.init()
            })
            break
          }
        }
      })
    }
  }
}
</script>
<style lang="scss" scoped>
</style>
