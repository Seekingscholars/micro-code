<template>
  <div class="dashboard-container">
    <el-card class="dashboard-card">
      <div class="dashboard-header">
        <div class="dashboard-title"><span>我的应用</span>
          <el-tooltip
            content="应用分组设置"
            effect="dark"
            placement="right"
          >
            <i
              class="dashboard-title-setting el-icon-setting"
              style="cursor: pointer"
              @click="createCategoryApp"
            />
          </el-tooltip>
        </div>
        <div class="dashboard-action">
          <div class="dashboard-action-item">
            <el-input
              v-model="name"
              placeholder="请输入应用名称进行搜索"
              prefix-icon="el-icon-search"
              type="text"
              clearable
              @input="handleSearch"
            />
          </div>
          <div class="dashboard-action-item">
            <el-button
              icon="el-icon-plus"
              type="primary"
              @click="createApp"
            >新建应用</el-button>
          </div>
        </div>
      </div>
      <div
        v-for="item in filterAppList"
        :key="item.category.id"
        class="dashboard-body"
      >
        <div v-if="item.appList&&item.appList.length>0">
          <div class="dashboard-sub-title">{{ item.category.categoryName }}</div>
          <div class="dashboard-sub-body">
            <div
              v-for="app in item.appList"
              :key="app.id"
              :class="{'dashboard-sub-body-item':true,'dashboard-sub-body-item-selected':selectAppId===app.id}"
              @click="()=>gotoApp(app)"
            >
              <div
                class="dashboard-action-item-setting"
                @click.stop="selectAppId=app.id"
              >
                <el-popover
                  :value="selectAppId===app.id"
                  placement="bottom"
                  trigger="click"
                >
                  <div class="dashboard-action-item-setting-menu">
                    <div
                      class="dashboard-action-item-setting-menu-item"
                      @click="()=>handleEditApp(app)"
                    ><i class="el-icon-edit" /><span
                      class="dashboard-action-item-setting-menu-item-title"
                    >编辑</span></div>
                    <div
                      class="dashboard-action-item-setting-menu-item"
                      style="color: #ef5c5c"
                      @click="()=>handleDeleteApp(app)"
                    ><i
                      class="el-icon-delete"
                    /><span class="dashboard-action-item-setting-menu-item-title">删除</span></div>
                  </div>
                  <div slot="reference"><i class="el-icon-setting" /></div>
                </el-popover>
              </div>
              <div
                class="dashboard-sub-body-item-app"
                :style="{backgroundColor:app.color}"
              >
                <svg-icon
                  class="svg-icon"
                  :icon-class="app.imageName"
                />
              </div>
              <div class="dashboard-sub-body-item-title">{{ app.appName }}</div>
            </div>
          </div>
        </div>
      </div>
    </el-card>
    <CreateEmptyAppDialog
      v-if="showAppDialogFlag"
      :visible.sync="showAppDialogFlag"
      :app="app"
      @ok="listApp"
    />
    <CreateCategoryDrawer
      v-if="showAppDrawerFlag"
      :visible.sync="showAppDrawerFlag"
      @ok="listApp"
    />
  </div>
</template>
<script>
import CreateEmptyAppDialog from './CreateEmptyAppDialog'
import CreateCategoryDrawer from './CreateCategoryDrawer'
import categoryApi from '@/api/category'
import appApi from '@/api/app'

export default {
  name: 'Dashboard',
  components: { CreateEmptyAppDialog, CreateCategoryDrawer },
  data() {
    return {
      name: '',
      appList: [],
      filterAppList: [],
      selectAppId: null,
      app: null,
      showAppDialogFlag: false,
      showAppDrawerFlag: false

    }
  },
  created() {
    this.listApp()
  },
  mounted() {
    document.addEventListener('click', this.handleDocumentClick, false)
  },
  destroyed() {
    document.removeEventListener('click', this.handleDocumentClick, false)
  },
  methods: {
    async listApp() {
      this.filterAppList = this.appList = await categoryApi.listApp() || []
    },
    gotoApp(app) {
      const to = this.$router.resolve({ path: '/app/overview/' + app.id })
      window.open(to.href, '_self')
    },
    handleSearch() {
      if (this.name) {
        this.filterAppList = []
        for (const app of this.appList) {
          const filterAppList = app.appList.filter(item => item.appName.indexOf(this.name) >= 0)
          if (filterAppList && filterAppList.length > 0) {
            this.filterAppList.push({ category: app.category, appList: filterAppList })
          }
        }
      } else {
        this.filterAppList = this.appList
      }
    },
    handleDocumentClick() {
      this.selectAppId = null
    },
    createApp() {
      this.app = null
      this.showAppDialogFlag = true
    },
    handleEditApp(app) {
      this.selectAppId = null
      this.app = app
      this.showAppDialogFlag = true
    },
    handleDeleteApp(app) {
      this.selectAppId = null
      this.$confirm('您确定要删除“' + app.appName + '”吗？', '警告', { type: 'warning' }).then(async() => {
        await appApi.remove({ id: app.id })
        this.listApp()
      })
    },
    createCategoryApp() {
      this.showAppDrawerFlag = true
    }
  }
}
</script>

<style lang="scss" scoped>
$setting-color: #0db3a6;
.dashboard-container {
}

.dashboard-card {
  margin: 20px;
}

.dashboard-header {
  display: flex;
  justify-content: space-between;
  height: 40px;
}

.dashboard-title {
  font-weight: bold;
  font-size: 18px;
}

.dashboard-title-setting {
  margin-left: 5px;
}

.dashboard-title-setting:hover {
  color: $setting-color;
}
.dashboard-body{
  padding: 10px;
}
.dashboard-action {
  display: flex;
  justify-content: flex-end;
}

.dashboard-action-item {
  margin-left: 20px;
}

.dashboard-sub-title {
  font-weight: bold;
  font-size: 14px;
  margin-bottom: 10px;
}

.dashboard-sub-body {
  display: flex;
  flex-wrap: wrap;
}

.dashboard-sub-body-item {
  cursor: pointer;
  width: 150px;
  padding-top: 30px;
  position: relative;
}

.dashboard-action-item-setting {
  position: absolute;
  right: 10px;
  top: 10px;
  display: none;
}

.dashboard-action-item-setting:hover {
  color: $setting-color;
}

.dashboard-sub-body-item:hover, .dashboard-sub-body-item-selected {
  box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.2);
}

.dashboard-sub-body-item:hover .dashboard-action-item-setting {
  display: inline-block;
}

.dashboard-sub-body-item-selected .dashboard-action-item-setting {
  display: inline-block;
}

.dashboard-sub-body-item-app {
  position: relative;
  margin: 0 auto;
  height: 48px;
  width: 48px;
  text-align: center;
  line-height: 48px;
  border-radius: 12px;
}
.svg-icon{
  color: #ffffff;
}
.dashboard-sub-body-item-title {
  margin: 15px auto;
  text-align: center;
}

.dashboard-action-item-setting-menu {
  padding: 2px;
}

.dashboard-action-item-setting-menu-item {
  padding: 5px;
  cursor: pointer;
}

.dashboard-action-item-setting-menu-item:hover {
  background-color: #eaecfd;
}

.dashboard-action-item-setting-menu-item-title {
  margin-left: 5px;
}
</style>
