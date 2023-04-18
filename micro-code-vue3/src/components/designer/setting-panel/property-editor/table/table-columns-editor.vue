<template>
  <div>
    <el-table
      v-sort="dragOption"
      :data="optionModel.columns"
      border
      row-key="id"
    >
      <el-table-column label="排序" width="50px">
        <div class="drag-handler" style="cursor: move">
          <el-icon>
            <Sort/>
          </el-icon>
        </div>
      </el-table-column>
      <el-table-column label="属性" prop="prop">
        <template #default="scope">
          <el-input v-model="scope.row.prop"/>
        </template>
      </el-table-column>
      <el-table-column label="标签" prop="label">
        <template #default="scope">
          <el-input v-model="scope.row.label"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="60px">
        <template #default="scope">
          <div class="operate">
            <el-icon style="cursor: pointer" title="编辑" @click="handleEdit(scope.row)">
              <Edit/>
            </el-icon>
            <el-icon style="color: red; cursor: pointer" title="删除" @click="handleDelete(scope.row)">
              <Delete/>
            </el-icon>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <el-button link @click="addNewColumn">添加一项+</el-button>
    <el-dialog
      v-model="showItemDialogFlag"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :destroy-on-close="true"
      :draggable="true"
      :show-close="false"
      class="small-padding-dialog"
      title="列编辑"
    >
      <el-form
        ref="form"
        :model="modelForm"
        label-position="left"
        label-width="80px"
      >
        <el-form-item label="属性" prop="prop">
          <el-input v-model="modelForm.prop"/>
        </el-form-item>
        <el-form-item label="标签" prop="label">
          <el-input v-model="modelForm.label"/>
        </el-form-item>
        <el-form-item label="宽度" prop="width">
          <el-input v-model="modelForm.width" type="number"/>
        </el-form-item>
        <el-form-item label="列类型" prop="type">
          <el-select v-model="modelForm.type" clearable style="width: 100%">
            <el-option
              v-for="item in rowTypeList"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="固定方式" prop="fixed">
          <el-select v-model="modelForm.fixed" clearable style="width: 100%">
            <el-option
              v-for="item in fixedList"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="对齐方式" prop="align">
          <el-select v-model="modelForm.align" clearable style="width: 100%">
            <el-option
              v-for="item in alignList"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="过长提示" prop="showOverflowTooltip">
          <el-switch v-model="modelForm.showOverflowTooltip"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="showItemDialogFlag = false"> 取消</el-button>
          <el-button type="primary" @click="saveItem"> 确定</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import {Delete, Edit, Sort} from "@element-plus/icons-vue";
import {generateId} from "@/utils/util";
import {option} from "./dragOption";

export default {
  name: "table-columns-editor",
  components: {Sort, Edit, Delete},
  props: {
    designer: Object,
    selectedWidget: Object,
    optionModel: Object
  },
  computed: {
    dragOption() {
      return option(this.optionModel.columns);
    }
  },
  data() {
    return {
      showItemDialogFlag: false,
      row: null,
      modelForm: {
        prop: "",
        label: "",
        width: "",
        type: "",
        fixed: "",
        align: "",
        showOverflowTooltip: null
      },
      rowTypeList: [
        {label: "选择", value: "selection"},
        {label: "下标", value: "index"},
        {label: "展开", value: "expand"}
      ],
      fixedList: [
        {label: "左侧", value: "left"},
        {label: "右侧", value: "right"}
      ],
      alignList: [
        {label: "居左", value: "left"},
        {label: "居中", value: "center"},
        {label: "居右", value: "right"}
      ]
    };
  },
  methods: {
    handleEdit(row) {
      this.row = row;
      this.modelForm = Object.assign(this.modelForm, row);
      this.showItemDialogFlag = true;
    },
    /** 删除操作 */
    handleDelete(row) {
      for (let i = 0; i < this.optionModel.columns.length; i++) {
        if (this.optionModel.columns[i].id === row.id) {
          this.optionModel.columns.splice(i, 1);
          break;
        }
      }
    },
    addNewColumn() {
      this.optionModel.columns.push({
        id: "column" + generateId(),
        prop: "",
        label: "",
        showOverflowTooltip: true,
        width: ""
      });
    },
    saveItem() {
      Object.assign(this.row, this.modelForm);
      this.showItemDialogFlag = false;
    }
  }
};
</script>

<style scoped>
.operate {
  display: flex;
  justify-content: space-around;
}
</style>
