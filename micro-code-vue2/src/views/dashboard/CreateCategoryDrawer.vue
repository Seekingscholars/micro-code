<template>
  <el-drawer
    :with-header="false"
    :wrapper-closable="false"
    destroy-on-close
    direction="ltr"
    size="500px"
    title="应用分组"
    v-bind="$attrs"
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
          :show-header="false"
          row-key="key"
          @cell-click="handleCellClick"
        >
          <el-table-column label="分组名称" prop="categoryName">
            <template slot-scope="scope">
              <div class="column">
                <el-input
                  v-show="selectKey===scope.row.key"
                  :ref="'input'+scope.row.key"
                  v-model="scope.row.categoryName"
                  placeholder="请输入分组名称"
                />
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
                <div class="toolbar-button" @click="()=>handleRemove(scope.row)">
                  <i class="el-icon-delete" style="color: red; cursor: pointer" title="删除" />
                </div>
              </div>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="scrollbar-bar">
        <el-button icon="el-icon-plus" type="text" @click="createCategory">添加</el-button>
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
import categoryApi from '@/api/category'

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
      const categoryList = await categoryApi.list() || []
      for (const category of categoryList) {
        category.key = this.index++
      }
      this.categoryList.length = 0
      this.categoryList.push(...categoryList)
    },
    createCategory() {
      this.categoryList.push({ key: this.index, id: '', categoryName: '', orderNo: '' })
      this.checkKey(this.index)
      this.index++
    },
    handleCellClick(row) {
      this.checkKey(row.key)
    },
    handleRemove(row) {
      for (let index = this.categoryList.length - 1; index >= 0; index--) {
        if (this.categoryList[index] === row) {
          this.categoryList.splice(index, 1)
          return
        }
      }
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
    async submit() {
      for (let i = 0; i < this.categoryList.length; i++) {
        this.categoryList[i].orderNo = i + 1
        delete this.categoryList[i].key
      }
      await categoryApi.saveBatch(this.categoryList)
      this.$emit('ok')
      this.close()
    }
  }
}
</script>

<style scoped>
.title {
  padding: 10px;
  font-size: 16px;
  text-align: center;
  font-weight: bold;
}

.scrollbar {
  height: calc(100vh - 100px);
}

.scrollbar-div {
  height: 100%;
  padding-left: 20px;
  padding-right: 20px;
}

.column {
  height: 65px;
  line-height: 65px;
}

.scrollbar-bar {
  display: flex;
  justify-content: flex-end;
  padding-right: 20px;
}

.toolbar {
  display: flex;
}

.toolbar-button {
  margin-right: 20px;
}

.footer {
  position: absolute;
  bottom: 5px;
  right: 10px;
}
</style>
