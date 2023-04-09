<template>
  <div>
    <ElTableDraggable ref="ElTableDraggable" :list="optionModel.items" handle=".drag-handler">
      <el-table :data="optionModel.items" border row-key="id">
        <el-table-column label="排序" width="50px">
          <i class="drag-handler el-icon-sort" style="cursor: move"></i>
        </el-table-column>
        <el-table-column label="属性" prop="value">
          <template slot-scope="scope">
            <el-input v-model="scope.row.value"></el-input>
          </template>
        </el-table-column>
        <el-table-column label="类型" prop="type">
          <template slot-scope="scope">
            <el-select v-model="scope.row.type" style="width: 100%">
              <el-option v-for="item in typeList" :key="item.value" :label="item.label" :value="item.value"></el-option>
            </el-select>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="60px">
          <template slot-scope="scope">
            <div class="operate">
              <i class="el-icon-edit" style="cursor: pointer" title="编辑" @click="handleEdit(scope.row)"></i>
              <i class="el-icon-delete" style="color: red;cursor: pointer" title="删除" @click="handleDelete(scope.row)"
              ></i>
            </div>
          </template>
        </el-table-column>
      </el-table>
      <el-button type="text" @click="addNewItem">添加一项+</el-button>
    </ElTableDraggable>
    <el-dialog v-if="showItemDialogFlag" v-dialog-drag
               :close-on-click-modal="false" :close-on-press-escape="false" :destroy-on-close="true" :show-close="false"
               :visible.sync="showItemDialogFlag"
               append-to-body class="small-padding-dialog" title="样式编辑"
    >
      <el-form ref="form" :model="modelForm" label-position="left" label-width="80px">
        <el-form-item label="属性" prop="value">
          <el-input type="text" v-model="modelForm.value"></el-input>
        </el-form-item>
        <el-form-item label="标签" prop="label">
          <el-input type="text" v-model="modelForm.label"></el-input>
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-select v-model="modelForm.type" style="width: 100%">
            <el-option v-for="item in typeList" :key="item.value" :label="item.label" :value="item.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="颜色" prop="color">
        <el-input type="color" v-model="modelForm.color"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="showItemDialogFlag = false">
          取消
        </el-button>
        <el-button type="primary" @click="saveItem">
          确定
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import ElTableDraggable from '../../ElTableDraggable/index.vue'
import { generateId } from '@/utils/util'

export default {
  name: 'chart-items-editor',
  props: {
    optionModel: Object
  },
  components: { ElTableDraggable },
  data() {
    return {
      showItemDialogFlag:false,
      modelForm: {
        value:'',
        label:'',
        type:'',
        color:''
      },
      typeList: [
        { value: 'bar', label: '柱状图' },
        { value: 'line', label: '折线图' },
        { value: 'pie', label: '饼图' },
        { value: 'scatter', label: '散点图' }
      ]
    }
  },
  methods: {
    handleEdit(row) {
      this.row = row
      this.modelForm = Object.assign(this.modelForm, row)
      this.showItemDialogFlag = true
    },
    /** 删除操作 */
    handleDelete(row) {
      this.$modal.confirm('是否确认删除？').then(() => {
        for (let i = 0; i < this.optionModel.items.length; i++) {
          if (this.optionModel.items[i].value === row.value) {
            this.optionModel.items.splice(i, 1)
            this.$nextTick(() => {
              this.$refs.ElTableDraggable.init()
            })
            break
          }
        }
      })
    },
    addNewItem() {
      this.optionModel.items.push({
        id:'item'+generateId(),
        value: '',
        label: '',
        disabled: false
      })
      this.$nextTick(() => {
        this.$refs.ElTableDraggable.init()
      })
    },
    saveItem() {
      Object.assign(this.row, this.modelForm)
      this.showItemDialogFlag = false
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
