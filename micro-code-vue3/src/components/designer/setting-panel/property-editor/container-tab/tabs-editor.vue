<template>
  <el-table :data="optionModel.tabs" border row-key="value">
    <el-table-column label="排序" width="50px">
      <div class="drag-handler" style="cursor: move">
        <el-icon>
          <Sort/>
        </el-icon>
      </div>
    </el-table-column>
    <el-table-column label="标签" prop="label">
      <template #default="scope">
        <el-input v-model="scope.row.label"/>
      </template>
    </el-table-column>
    <el-table-column label="操作" width="60px">
      <template #default="scope">
        <div class="operate">
          <i
            class="el-icon-delete"
            style="color: red; cursor: pointer"
            title="删除"
            @click="deleteTabPane(scope.row)"
          ></i>
        </div>
      </template>
    </el-table-column>
  </el-table>
  <el-button link @click="addTabPane">添加一项+</el-button>
</template>
<script>
import {Sort} from "@element-plus/icons-vue";
import {deepClone} from "@/utils/util";

export default {
  name: "tabs-editor",
  componentName: "PropertyEditor",
  components: {Sort},
  props: {
    optionModel: Object
  },
  methods: {
    addTabPane() {
      const newTab = deepClone(this.optionModel.tabs[0]);
      newTab.value = "tab" + (this.optionModel.tabs.length + 1);
      newTab.label = "标签" + (this.optionModel.tabs.length + 1);
      this.optionModel.tabs.push(newTab);
    },
    deleteTabPane(row) {
      if (this.optionModel.tabs.length === 1) {
        this.$message.info("仅剩一个选项卡页不可删除");
        return;
      }
      this.$modal.confirm("是否确认删除？").then(() => {
        for (let i = 0; i < this.optionModel.tabs.length; i++) {
          if (this.optionModel.tabs[i].value === row.value) {
            this.optionModel.tabs.splice(i, 1);
            break;
          }
        }
      });
    }
  }
};
</script>
<style lang="scss" scoped></style>
