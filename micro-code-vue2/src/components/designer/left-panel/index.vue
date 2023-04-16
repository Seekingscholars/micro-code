<template>
  <el-tabs
    v-model="activeTab"
    tab-position="left"
    :class="activeTab ? 'showTab' : 'hideTab'"
    @tab-click="handleTabClick"
  >
    <el-tab-pane label="大纲树" name="outline">
      <div slot="label">
        <el-tooltip content="大纲树" placement="right">
          <div class="icon-label"><i class="el-icon-data-board" /></div>
        </el-tooltip>
      </div>
      <el-scrollbar height="calc(100vh - 112px)">
        <OutlinePanel ref="OutlinePanelRef" :designer="designer" />
      </el-scrollbar>
    </el-tab-pane>
    <el-tab-pane label="组件库" name="componentLib">
      <div slot="label">
        <el-tooltip content="组件库" placement="right">
          <div class="icon-label"><i class="el-icon-office-building" /></div>
        </el-tooltip>
      </div>
      <el-scrollbar height="calc(100vh - 112px)">
        <WidgetPanel :designer="designer" />
      </el-scrollbar>
    </el-tab-pane>
    <el-tab-pane label="全局变量" name="globalVariable">
      <div slot="label">
        <el-tooltip content="全局变量" placement="right">
          <div class="icon-label"><i class="el-icon-cloudy" /></div>
        </el-tooltip>
      </div>
      <el-scrollbar height="calc(100vh - 112px)">
        <VariablePanel ref="VariablePanelRef" />
      </el-scrollbar>
    </el-tab-pane>
    <el-tab-pane label="数据源" name="datasource">
      <div slot="label">
        <el-tooltip content="数据源" placement="right">
          <div class="icon-label"><i class="el-icon-coin" /></div>
        </el-tooltip>
      </div>
      <el-scrollbar height="calc(100vh - 112px)">
        <DatasourcePanel :designer="designer" />
      </el-scrollbar>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
import OutlinePanel from './outline-panel/index.vue'
import WidgetPanel from './widget-panel/index.vue'
import VariablePanel from './variable-panel/index.vue'
import DatasourcePanel from './datasource-panel/index.vue'

export default {
  name: 'LeftPanel',
  components: {
    OutlinePanel,
    WidgetPanel,
    VariablePanel,
    DatasourcePanel
  },
  props: {
    designer: Object
  },
  data() {
    return {
      activeTab: 'componentLib'
    }
  },
  methods: {
    handleTabClick(tab) {
      switch (tab.name) {
        case 'globalVariable':
          this.$refs.VariablePanelRef.refresh()
          break
      }
    }
  }
}
</script>
<style scoped>
.showTab {
  height: 100%;
  width: 340px !important;
}

.hideTab {
  height: 100%;
  width: 60px !important;
}

.icon-label {
  font-size: 20px;
}
</style>
