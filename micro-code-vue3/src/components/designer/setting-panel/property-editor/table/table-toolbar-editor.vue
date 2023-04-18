<template>
  <div>

    <el-table :data="optionModel.toolbar" border row-key="id">
      <el-table-column label="排序" width="50px">
        <i class="drag-handler el-icon-sort" style="cursor: move"></i>
      </el-table-column>
      <el-table-column label="标签" prop="label">
        <template #default="scope">
          <el-input v-model="scope.row.label"></el-input>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="60px">
        <template #default="scope">
          <div class="operate">
            <i class="el-icon-edit" style="cursor: pointer" title="编辑" @click="handleEdit(scope.row)"></i>
            <i class="el-icon-delete" style="color: red;cursor: pointer" title="删除"
               @click="handleDelete(scope.row)"
            ></i>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <el-button link @click="addNewColumn">添加一项+</el-button>

    <el-dialog v-if="showItemDialogFlag" :close-on-click-modal="false"
               :close-on-press-escape="false" :destroy-on-close="true" :draggable="true" :show-close="false"
               :visible.sync="showItemDialogFlag"
               append-to-body class="small-padding-dialog" title="按钮编辑"
    >
      <el-form ref="form" :model="modelForm" label-position="left" label-width="80px">
        <el-form-item label="标签" prop="label">
          <el-input v-model="modelForm.label"></el-input>
        </el-form-item>
        <el-form-item label="图标" prop="icon">
          <el-input v-model="modelForm.icon"></el-input>
        </el-form-item>
        <el-form-item label="点击事件" prop="click">
          <el-alert :closable="false" title="function(row){" type="info"></el-alert>
          <code-editor ref="gfEditor" v-model="modelForm.click" :mode="'javascript'" :readonly="false"></code-editor>
          <el-alert :closable="false" title="}" type="info"></el-alert>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="showItemDialogFlag = false">
          取消
        </el-button>
        <el-button type="primary" @click="handleSave">
          确定
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {generateId} from '@/utils/util'

import CodeEditor from '@/components/code-editor/index.vue'

export default {
  name: 'table-toolbar-editor',
  props: {
    designer: Object,
    selectedWidget: Object,
    optionModel: Object
  },
  components: {CodeEditor},
  data() {
    return {
      showItemDialogFlag: false,
      row: null,
      modelForm: {
        label: '',
        icon: '',
        color: '',
        tooltip: '',
        click: ''
      }
    }
  },
  methods: {
    handleEdit(row) {
      this.row = row
      this.modelForm = Object.assign(this.modelForm, row)
      this.showItemDialogFlag = true
    },
    handleSave() {
      Object.assign(this.row, this.modelForm)
      this.showItemDialogFlag = false
    },
    /** 删除操作 */
    handleDelete(row) {
      this.$modal.confirm('是否确认删除？').then(() => {
        for (let i = 0; i < this.optionModel.toolbar.length; i++) {
          if (this.optionModel.toolbar[i].id === row.id) {
            this.optionModel.toolbar.splice(i, 1)

            break
          }
        }
      })
    },
    addNewColumn() {
      this.optionModel.toolbar.push({id: 'button' + generateId(), label: '', title: '', icon: '', click: ''})

    }

  }
}
</script>

<style scoped>
.operate {
  display: flex;
  justify-content: space-between;
}
</style>
