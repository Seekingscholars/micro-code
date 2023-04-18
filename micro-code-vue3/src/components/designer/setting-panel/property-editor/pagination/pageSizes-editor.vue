<template>
  <div>
    <el-table :data="optionModel.pageSizes" border>
      <el-table-column label="页数" prop="pageSize">
        <template #default="scope">
          <el-input v-model="scope.row.pageSize" type="number"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="60px">
        <template #default="scope">
          <div class="operate">
            <i
              class="el-icon-delete"
              style="color: red; cursor: pointer"
              title="删除"
              @click="deletePageSize(scope.row)"
            ></i>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <el-button link @click="addPageSize">添加一项+</el-button>
  </div>
</template>
<script>
export default {
  name: "PageSizesEditor",
  componentName: "PropertyEditor",
  props: {
    optionModel: Object
  },
  methods: {
    addPageSize() {
      this.optionModel.pageSizes.push({pageSize: null});
    },
    deletePageSize(row) {
      if (this.optionModel.pageSizes.length === 1) {
        this.$message.info("仅剩一个页数不可删除");
        return;
      }
      for (let i = 0; i < this.optionModel.pageSizes.length; i++) {
        if (this.optionModel.pageSizes[i].pageSize === row.pageSize) {
          this.optionModel.pageSizes.splice(i, 1);
          break;
        }
      }
    }
  }
};
</script>
