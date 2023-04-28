<template>
  <div>
    <el-table :data="dataList" border>
      <el-table-column label="数据源名称" prop="" />
      <el-table-column label="数据源地址" prop="" />
      <el-table-column label="操作" width="60px">
        <template slot-scope="scope">
          <div class="operate">
            <i class="el-icon-edit" style="cursor: pointer" title="编辑" @click="handleEdit(scope.row)" />
            <i
              class="el-icon-delete"
              style="color: red;cursor: pointer"
              title="删除"
              @click="handleDelete(scope.row)"
            />
          </div>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: 'Datasource',
  data() {
    return {
      dataList: []
    }
  }
}
</script>

<style scoped>

</style>
