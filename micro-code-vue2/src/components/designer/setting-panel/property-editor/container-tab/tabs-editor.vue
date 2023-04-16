<template>
  <ElTableDraggable ref="ElTableDraggable" :list="optionModel.tabs" handle=".drag-handler">
    <el-table :data="optionModel.tabs" border row-key="value">
      <el-table-column label="排序" width="50px">
        <i class="drag-handler el-icon-sort" style="cursor: move"></i>
      </el-table-column>
      <!--      <el-table-column label="属性" prop="value">-->
      <!--        <template slot-scope="scope">-->
      <!--          <el-input v-model="scope.row.value"></el-input>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
      <el-table-column label="标签" prop="label">
        <template slot-scope="scope">
          <el-input v-model="scope.row.label"></el-input>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="60px">
        <template slot-scope="scope">
          <div class="operate">
            <i class="el-icon-delete" style="color: red;cursor: pointer" title="删除" @click="deleteTabPane(scope.row)"
            ></i>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <el-button type="text" @click="addTabPane">添加一项+</el-button>
  </ElTableDraggable>
</template>
<script>
import Draggable from 'vuedraggable'
import { deepClone } from '@/utils/util'
import ElTableDraggable from '../../ElTableDraggable'

export default {
  name: 'tabs-editor',
  componentName: 'PropertyEditor',
  components: {
    Draggable,
    ElTableDraggable
  },
  props: {
    optionModel: Object
  },
  methods: {
    addTabPane() {
      const newTab = deepClone(this.optionModel.tabs[0])
      newTab.value = 'tab' + (this.optionModel.tabs.length + 1)
      newTab.label = '标签' + (this.optionModel.tabs.length + 1)
      this.optionModel.tabs.push(newTab)
    },
    deleteTabPane(row) {
      if (this.optionModel.tabs.length === 1) {
        this.$message.info('仅剩一个选项卡页不可删除')
        return
      }
      this.$modal.confirm('是否确认删除？').then(() => {
        for (let i = 0; i < this.optionModel.tabs.length; i++) {
          if (this.optionModel.tabs[i].value === row.value) {
            this.optionModel.tabs.splice(i, 1)
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
