<template>
  <el-drawer
    v-bind="$attrs"
    title="应用分组"
    size="calc(100% - 50px)"
    direction="btt"
    @close="close"
  >
    <el-container>
      <el-main>
        <div class="dashboard-drawer">
          <div class="dashboard-drawer-group">
            <div class="dashboard-body">
              <div class="dashboard-sub-title">默认分组</div>
              <div class="dashboard-sub-wrapper">
                <div class="dashboard-sub-body">
                  <div v-for="item in appList" :key="item.id" class="dashboard-sub-body-item">
                    <div class="dashboard-sub-body-item-app" />
                    <div class="dashboard-sub-body-item-title">智能分析</div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </el-main>
      <el-aside>
        <el-table
          v-sort="dragOption"
          :data="categoryList"
          row-key="id"
          :show-header="false"
        >
          <el-table-column label="排序" width="50px">
            <div class="drag-handler" style="cursor: move">
              <i class="el-icon-sort" />
            </div>
          </el-table-column>
          <el-table-column label="名称" prop="categoryName">
            <template slot-scope="scope">
              <el-input v-model="scope.row.categoryName" />
            </template>
          </el-table-column>
          <el-table-column label="操作" width="60px">
            <template slot-scope="scope">
              <div class="operate">
                <i class="el-icon-delete" title="删除" style="color: red; cursor: pointer" />
              </div>
            </template>
          </el-table-column>
        </el-table>
        <el-button type="text" icon="el-icon-plus">添加</el-button>
      </el-aside>
    </el-container>
  </el-drawer>
</template>

<script>
import { option } from '@/utils/dragOption'
export default {
  name: 'CreateCategoryDrawer',
  data() {
    return {
      appList: [
        {
          id: 1
        },
        {
          id: 2
        }
      ],
      categoryList: [{ id: 1, categoryName: '默认分组' }]
    }
  },
  computed: {
    dragOption() {
      return option(this.categoryList)
    }
  },
  methods: {
    close() {
      this.$emit('update:visible', false)
    }
  }
}
</script>

<style scoped>
.dashboard-sub-wrapper{
  padding: 10px;
  border: 1px dashed #0db3a6;
}

.dashboard-sub-title{
  font-weight: bold;
  font-size: 14px;
  margin-bottom: 10px;
}
.dashboard-sub-body{
  display: flex;
  flex-wrap: wrap;
}
.dashboard-sub-body-item{
  cursor: pointer;
  width: 200px;
  padding-top: 30px;
  position: relative;
}
.dashboard-sub-body-item:hover{
  box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.2);
}
.dashboard-sub-body-item-app{
  position: relative;
  margin: 0 auto;
  height: 48px;
  width: 48px;
  background-color: #d5a60a;
  background-image: url("../../assets/images/icon-cover-1.png");
  background-size: 100%;
  background-repeat: no-repeat;
  border-radius: 12px;
}
.dashboard-sub-body-item-title{
  margin: 15px auto;
  text-align: center;
}
</style>
