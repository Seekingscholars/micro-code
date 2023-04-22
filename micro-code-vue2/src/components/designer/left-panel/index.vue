<template>
  <div class="left-panel">
    <el-tabs
      v-model="activeTab"
      :class="activeTab ? 'showTab' : 'hideTab'"
      tab-position="left"
      @tab-click="handleTabClick"
    >
      <el-tab-pane label="大纲树" name="outline">
        <div slot="label">
          <el-tooltip content="大纲树" placement="right">
            <div class="icon-label"><i class="el-icon-data-board" /></div>
          </el-tooltip>
        </div>
        <el-scrollbar class="scrollbar">
          <OutlinePanel ref="OutlinePanelRef" :designer="designer" />
        </el-scrollbar>
      </el-tab-pane>
      <el-tab-pane label="组件库" name="componentLib">
        <div slot="label">
          <el-tooltip content="组件库" placement="right">
            <div class="icon-label"><i class="el-icon-office-building" /></div>
          </el-tooltip>
        </div>
        <el-scrollbar class="scrollbar">
          <WidgetPanel :designer="designer" />
        </el-scrollbar>
      </el-tab-pane>
      <el-tab-pane label="全局变量" name="globalVariable">
        <div slot="label">
          <el-tooltip content="全局变量" placement="right">
            <div class="icon-label"><i class="el-icon-cloudy" /></div>
          </el-tooltip>
        </div>
        <el-scrollbar class="scrollbar">
          <VariablePanel ref="VariablePanelRef" />
        </el-scrollbar>
      </el-tab-pane>
      <slot name="left" :designer="designer" />
    </el-tabs>
  </div>
</template>

<script>
import OutlinePanel from './outline-panel/index.vue'
import WidgetPanel from './widget-panel/index.vue'
import VariablePanel from './variable-panel/index.vue'

export default {
  name: 'LeftPanel',
  components: {
    OutlinePanel,
    WidgetPanel,
    VariablePanel
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
<style lang="scss">
.left-panel{
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
  .scrollbar{
    height: calc(100vh - 48px);
  }
}
</style>
