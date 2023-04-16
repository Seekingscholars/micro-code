<template>
  <div class="dashboard-container">
    <el-card class="dashboard-card">
      <div class="dashboard-header">
        <div class="dashboard-title"><span>我的应用</span>
          <el-tooltip content="应用分组设置" placement="right" effect="dark">
            <i class="dashboard-title-setting el-icon-setting" style="cursor: pointer" @click="createCategoryApp" />
          </el-tooltip>
        </div>
        <div class="dashboard-action">
          <div class="dashboard-action-item">
            <el-input v-model="name" type="text" placeholder="请输入名称进行搜索" prefix-icon="el-icon-search" />
          </div>
          <div class="dashboard-action-item">
            <el-button type="primary" icon="el-icon-plus" @click="createApp">新建应用</el-button>
          </div>
        </div>
      </div>
      <div class="dashboard-body">
        <div class="dashboard-sub-title">默认分组</div>
        <div class="dashboard-sub-body">
          <div v-for="item in appList" :key="item.id" :class="{'dashboard-sub-body-item':true,'dashboard-sub-body-item-selected':selectAppId===item.id}" @click="gotoApp">
            <div class="dashboard-action-item-setting" @click.stop="selectAppId=item.id">
              <el-popover
                placement="bottom"
                trigger="click"
                :value="selectAppId===item.id"
              >
                <div class="dashboard-action-item-setting-menu">
                  <div class="dashboard-action-item-setting-menu-item"><i class="el-icon-edit" /><span class="dashboard-action-item-setting-menu-item-title">编辑</span></div>
                  <div class="dashboard-action-item-setting-menu-item" style="color: #ef5c5c"><i class="el-icon-delete" /><span class="dashboard-action-item-setting-menu-item-title">删除</span></div>
                </div>
                <div slot="reference"><i class="el-icon-setting" /></div>
              </el-popover>
            </div>
            <div class="dashboard-sub-body-item-app" />
            <div class="dashboard-sub-body-item-title">智能分析</div>
          </div>
        </div>
      </div>
    </el-card>
    <CreateAppDialog :visible.sync="showAppDialogFlag" />
    <CreateCategoryDrawer :visible.sync="showAppDrawerFlag" />
  </div>
</template>
<script>
import CreateAppDialog from './CreateAppDialog'
import CreateCategoryDrawer from './CreateCategoryDrawer'
export default {
  name: 'Dashboard',
  components: { CreateAppDialog, CreateCategoryDrawer },
  data() {
    return {
      name: '',
      appList: [
        {
          id: 1
        },
        {
          id: 2
        }
      ],
      selectAppId: null,
      showAppDialogFlag: false,
      showAppDrawerFlag: false

    }
  },
  mounted() {
    document.addEventListener('click', this.handleDocumentClick, false)
  },
  destroyed() {
    document.removeEventListener('click', this.handleDocumentClick, false)
  },
  methods: {
    gotoApp() {
      const to = this.$router.resolve({ name: 'app' })
      window.open(to.href, '_self')
    },
    handleDocumentClick() {
      this.selectAppId = null
    },
    createApp() {
      this.showAppDialogFlag = true
    },
    createCategoryApp() {
      this.showAppDrawerFlag = true
    }
  }
}
</script>

<style lang="scss" scoped>
$setting-color:#0db3a6;
.dashboard-container{
  padding: 10px;
}
.dashboard-card{
  margin: 10px;
}
.dashboard-header{
  display: flex;
  justify-content: space-between;
  height: 40px;
}
.dashboard-title{
  font-weight: bold;
  font-size: 18px;
}
.dashboard-title-setting{
  margin-left: 5px;
}
.dashboard-title-setting:hover{
  color: $setting-color;
}
.dashboard-action{
  display: flex;
  justify-content: flex-end;
}
.dashboard-action-item{
  margin-left: 20px;
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
.dashboard-action-item-setting{
  position: absolute;
  right: 10px;
  top: 10px;
  display: none;
}
.dashboard-action-item-setting:hover{
  color: $setting-color;
}
.dashboard-sub-body-item:hover,.dashboard-sub-body-item-selected{
  box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.2);
}
.dashboard-sub-body-item:hover .dashboard-action-item-setting{
  display: inline-block;
}
.dashboard-sub-body-item-selected .dashboard-action-item-setting{
  display: inline-block;
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
.dashboard-action-item-setting-menu{
  padding: 2px;
}
.dashboard-action-item-setting-menu-item{
  padding: 5px;
  cursor: pointer;
}
.dashboard-action-item-setting-menu-item:hover{
  background-color: #eaecfd;
}
.dashboard-action-item-setting-menu-item-title{
  margin-left: 5px;
}
</style>
