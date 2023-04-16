<template>
  <div>
    <el-table :data="dataList" v-bind="field.options">
      <el-table-column v-for="(item,index) in field['table-column'].columns" :key="index" v-bind="item"/>
      <el-table-column
        v-if="!field['table-toolbar'].disabled" :label="field['table-toolbar'].label"
        :width="field['table-toolbar'].width?field['table-toolbar'].width:''"
      >
        <template slot-scope="scope">
          <el-button v-for="(item,index) in showToolbar" :key="index" :icon="item.icon" size="mini"
                     type="text" @click="callClick(item.click,scope.row)"
          >{{ item.label }}
          </el-button>
          <el-dropdown v-if="showMore" size="mini">
            <el-button icon="el-icon-d-arrow-right" size="mini" type="text">更多</el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item v-for="(item,index) in showMoreToolbar" :key="index">{{ item.label }}
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>
    <div v-if="!field.pagination.disabled" class="pagination">
      <el-pagination
        :current-page="currentPage"
        :page-size="pageSize"
        :page-sizes="pageSizes"
        :total="total"
        layout="total, sizes, prev, pager, next, jumper"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      >
      </el-pagination>
    </div>
  </div>
</template>
<script>
import fieldMixin from './fieldMixin'

export default {
  name: 'table-widget',
  componentName: 'FieldWidget',
  mixins: [fieldMixin],
  props: {
    field: Object
  },
  data() {
    return {
      total: 0,
      currentPage: 1
    }
  },
  computed: {
    pageSize() {
      return parseInt(this.field.pagination.pageSize)
    },
    dataList() {
      const data = this.field.options.data
      const disabled = this.field.pagination.disabled
      let dataList
      if (!!data) {
        let customFn = new Function(this.wrapWith('return ' + data))
        dataList = customFn.call(this)
      }
      if (dataList && !disabled) {
        const currentPage = this.currentPage
        const pageSize = this.field.pagination.pageSize
        this.total = dataList.length
        dataList = dataList.slice((currentPage - 1) * pageSize, currentPage * pageSize)
      }
      return dataList || []
    },
    showToolbar() {
      const toolbar = this.field['table-toolbar'].toolbar
      const showCount = this.field['table-toolbar'].showCount
      if (showCount) {
        return toolbar.slice(0, showCount)
      }
      return toolbar
    },
    showMore() {
      const toolbar = this.field['table-toolbar'].toolbar
      const showCount = this.field['table-toolbar'].showCount
      if (showCount) {
        return toolbar.length > showCount
      }
      return false
    },
    showMoreToolbar() {
      const toolbar = this.field['table-toolbar'].toolbar
      const showCount = this.field['table-toolbar'].showCount
      if (showCount) {
        return toolbar.slice(showCount)
      }
      return []
    },
    pageSizes() {
      return this.field.pagination.pageSizes.map(page => page.pageSize)
    }
  },
  created() {
    try {
      this.dataList()
    } catch (e) {
      const message = e.message
      if (message && message.endsWith('is not defined')) {
        const index = message.indexOf(' ')
        this.$set(this.$model, message.substr(0, index), [])
      }
    }
  },
  methods: {
    // 更多操作触发
    handleCommand(command, row) {
      switch (command) {
        case 'handleResetPwd':
          break
        default:
          break
      }
    },
    callClick(click, row) {
      if (click) {
        new Function('row', this.wrapWith(click)).call(this, row)
      }
    },
    handleSizeChange(value) {
      this.field.pagination.pageSize = value
    },
    handleCurrentChange(value) {
      this.currentPage = value
    }
  }
}
</script>

<style lang="scss" scoped>
.pagination {
  display: flex;
  justify-content: flex-end;
}

</style>
