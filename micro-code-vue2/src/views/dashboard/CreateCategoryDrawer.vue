<template>
  <el-drawer
    v-bind="$attrs"
    title="应用分组"
    size="500px"
    direction="ltr"
    destroy-on-close
    :with-header="false"
    :wrapperClosable="false"
    @close="close"
  >
    <div class="title">
      <span>应用分组设置</span>
    </div>
    <el-scrollbar class="scrollbar">
      <div class="scrollbar-div">
        <el-table
          v-sort="dragOption"
          :data="categoryList"
          row-key="key"
          :show-header="false"
          @cell-click="handleCellClick"
        >
          <el-table-column label="分组名称" prop="categoryName">
            <template slot-scope="scope">
              <div class="column">
                <el-input v-show="selectKey===scope.row.key" :ref="'input'+scope.row.key" v-model="scope.row.categoryName" />
                <span v-show="selectKey!=scope.row.key">{{ scope.row.categoryName }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="操作" width="60px">
            <template slot-scope="scope">
              <div class="toolbar">
                <div class="drag-handler toolbar-button" style="cursor: move">
                  <i class="el-icon-sort" title="排序" />
                </div>
                <div class="toolbar-button">
                  <i class="el-icon-delete" title="删除" style="color: red; cursor: pointer" />
                </div>
              </div>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="scrollbar-bar">
        <el-button type="text" icon="el-icon-plus" @click="createCategory">添加</el-button>
      </div>
    </el-scrollbar>
    <div class="footer">
      <el-button @click="close">取 消</el-button>
      <el-button type="primary" @click="submit">确 定</el-button>
    </div>
  </el-drawer>
</template>

<script>
import { option } from '@/utils/dragOption'
import categoryApi from './api/category.api'
export default {
  name: 'CreateCategoryDrawer',
  data() {
    return {
      categoryList: [],
      index: 1,
      selectKey: null
    }
  },
  computed: {
    dragOption() {
      return option(this.categoryList)
    }
  },
  created() {
    this.listCategory()
  },
  methods: {
    async listCategory() {
      this.categoryList = await categoryApi.list() || []
    },
    createCategory() {
      this.categoryList.push({ key: this.index, id: '', categoryName: '' })
      this.checkKey(this.index)
      this.index++
    },
    handleCellClick(row) {
      this.checkKey(row.key)
    },
    checkKey(key) {
      this.selectKey = key
      this.$nextTick(() => {
        const refs = this.$refs['input' + key]
        if (refs) {
          refs.focus()
        }
      })
    },
    close() {
      this.$emit('update:visible', false)
    },
    submit() {

    }
  }
}
</script>

<style scoped>
.title{
  padding: 10px;
  font-size: 16px;
  text-align: center;
  font-weight: bold;
}
.scrollbar{
  height: calc(100vh - 100px);
}
.scrollbar-div{
  height: 100%;
  padding-left: 20px;
  padding-right: 20px;
}
.column{
  height: 65px;
  line-height: 65px;
}
.scrollbar-bar{
  display: flex;
  justify-content: flex-end;
  padding-right: 20px;
}
.toolbar{
  display: flex;
}
.toolbar-button{
  margin-right: 20px;
}
.footer{
  position: absolute;
  bottom: 5px;
  right: 10px;
}
</style>
