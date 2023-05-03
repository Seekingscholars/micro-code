<template>
  <div class="container">
    <el-container>
      <el-main>
        <el-container>
          <el-header>
            <div class="header">
              <el-form
                ref="queryForm"
                :model="searchForm"
                size="small"
                :inline="true"
              >
                <el-form-item
                  label="数据源名称"
                  prop="name"
                >
                  <el-input
                    v-model="searchForm.name"
                    placeholder="请输入数据源名称"
                    clearable
                  />
                </el-form-item>
                <el-form-item
                  label="数据源类型"
                  prop="type"
                >
                  <el-select
                    v-model="searchForm.type"
                    placeholder="请输入数据源类型"
                    clearable
                    multiple
                  >
                    <el-option
                      v-for="item in databaseList"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value"
                    />
                  </el-select>
                </el-form-item>
                <el-form-item>
                  <el-button
                    type="primary"
                    icon="el-icon-search"
                    size="small"
                    @click="handleQuery"
                  >查询</el-button>
                </el-form-item>
              </el-form>
              <el-row
                :gutter="10"
              >
                <el-button
                  type="primary"
                  icon="el-icon-plus"
                  size="small"
                  @click="handleDatasource"
                >新增数据源</el-button>
              </el-row>
            </div>
          </el-header>
          <el-main>
            <el-table
              :height="tableHeight"
              :data="dataList"
            >
              <el-table-column
                type="index"
                width="55"
                align="center"
              />
              <el-table-column
                label="数据源名称"
                prop="name"
                width="200"
              />
              <el-table-column
                label="数据源类型"
                prop="type"
                width="200"
              >
                <template slot-scope="scope">
                  <div>{{ getDbTypeLabel(scope.row.type) }}</div>
                </template>
              </el-table-column>

              <el-table-column
                label="备注"
                prop="remark"
                width="300"
              />
              <el-table-column
                label="数据源地址"
                prop="url"
                :show-overflow-tooltip="true"
              />
              <el-table-column
                label="操作"
                width="150"
              >
                <template
                  slot-scope="scope"
                >
                  <el-button
                    size="mini"
                    type="text"
                    icon="el-icon-edit"
                    @click="handleUpdate(scope.row)"
                  >修改</el-button>
                  <el-button
                    size="mini"
                    type="text"
                    icon="el-icon-delete"
                    @click="handleDelete(scope.row)"
                  >删除</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-main>
          <el-footer>
            <div class="pagination">
              <el-pagination
                :total="total"
                :page.sync="searchForm.pageNum"
                :limit.sync="searchForm.pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                @pagination="getList"
              />
            </div>
          </el-footer>
        </el-container>
      </el-main>
    </el-container>
    <DatasourceDialog
      v-if="databaseOpen"
      :visible.sync="databaseOpen"
      :row="row"
      @change="getList"
    />
  </div>
</template>

<script>
import DatasourceDialog from './component/DatasourceDialog'
import { databaseList } from './component/db'
import datasourceApi from '@/api/datasource.api'
export default {
  name: 'Datasource',
  components: { DatasourceDialog },
  data() {
    return {
      databaseOpen: false,
      // 查询参数
      searchForm: {
        pageNum: 1,
        pageSize: 10,
        type: []
      },
      total: 0,
      row: null,
      dataList: []
    }
  },
  computed: {
    tableHeight: () => {
      return window.innerHeight - 250
    },
    databaseList() {
      return databaseList
    }
  },
  created() {
    this.getList()
  },
  methods: {
    async getList() {
      const res = await datasourceApi.page(this.searchForm)
      this.dataList = res.rows
      this.total = res.total
    },
    getDbTypeLabel(type) {
      for (const database of databaseList) {
        if (database.value === type) {
          return database.label
        }
      }
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.searchForm.pageNum = 1
      this.getList()
    },
    handleDatasource() {
      this.row = null
      this.databaseOpen = true
    },
    handleUpdate(row) {
      this.row = row
      this.databaseOpen = true
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      this.$modal.confirm('是否确认删除数据源名称为"' + row.name + '"的数据项？').then(async () => {
        await datasourceApi.remove({ id: row.id })
        await this.getList()
      })
    }
  }
}
</script>
<style scoped>
.header{
  display: flex;
  justify-content: space-between;
}
.pagination{
  display: flex;
  justify-content: flex-end;
}
</style>
