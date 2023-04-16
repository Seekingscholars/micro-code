<template>
  <el-dialog
    v-bind="$attrs"
    width="880px"
    :show-close="true"
    :close-on-click-modal="false"
    title="新建应用"
    @close="close"
  >
    <div class="dashboard-dialog-group">
      <div class="dashboard-dialog-group-title"><i class="el-icon-s-opportunity" /><span class="dashboard-dialog-group-name">猜你想要</span></div>
      <div class="dashboard-dialog-app">
        <div class="dashboard-dialog-template" @click="createEmptyApp">
          <div class="dashboard-dialog-font"><i class="el-icon-plus" /></div>
          <div class="dashboard-dialog-create">创建空白应用</div>
        </div>
        <div class="dashboard-dialog-template">产研</div>
        <div class="dashboard-dialog-template">项目</div>
        <div class="dashboard-dialog-template">售后</div>
      </div>
    </div>
    <div class="dashboard-dialog-group">
      <div class="dashboard-dialog-group-title"><i class="el-icon-star-on" /><span class="dashboard-dialog-group-name">热门模板</span></div>
      <div class="dashboard-dialog-app">
        <div class="dashboard-dialog-template">模板1</div>
        <div class="dashboard-dialog-template">模板2</div>
        <div class="dashboard-dialog-template">模板3</div>
        <div class="dashboard-dialog-template">模板4</div>
      </div>
    </div>
  </el-dialog>
</template>

<script>
export default {
  name: 'CreateAppDialog',
  data() {
    return {
      showAppDialogFlag: false
    }
  },
  methods: {
    close() {
      this.$emit('update:visible', false)
    },
    createEmptyApp() {

    }
  }
}
</script>

<style>

.dashboard-dialog-app{
  width: 100%;
  margin: 15px;
  display: flex;
  flex-wrap: wrap;
}
.dashboard-dialog-group-title{
  font-weight: bold;
  font-size: 16px;
}
.dashboard-dialog-group-name{
  margin-left: 5px;
}
.dashboard-dialog-template{
  border-radius: 12px;
  width: 195px;
  height: 120px;
  margin-left: 10px;
  background-color: #F5F6F8;
  cursor: pointer;
}
.dashboard-dialog-template:hover{
  box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.2);
  transform: translateY(-6px);
}
.dashboard-dialog-font{
  text-align: center;
  margin-top: 30px;
  color: #0db3a6;
  font-size: 32px;
}
.dashboard-dialog-create{
  margin-top: 10px;
  text-align: center;
  font-size: 14px;
  font-weight: bold;
}
</style>
