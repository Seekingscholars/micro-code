<template>
  <el-tabs
    v-model="activeTab"
    :class="activeTab ? 'showTab' : 'hideTab'"
    tab-position="left"
    @tab-click="handleTabClick"
  >
    <el-tab-pane label="大纲树" name="tree">
      <template #label>
        <el-tooltip content="大纲树" effect="dark" placement="right">
          <el-icon :size="20">
            <DataBoard/>
          </el-icon>
        </el-tooltip>
      </template>
    </el-tab-pane>
    <el-tab-pane label="组件库" name="componentLib">
      <template #label>
        <el-tooltip content="组件库" effect="dark" placement="right">
          <el-icon :size="20">
            <OfficeBuilding/>
          </el-icon>
        </el-tooltip>
      </template>
      <el-scrollbar height="calc(100vh - 112px)">
        <WidgetPanel :designer="designer"/>
      </el-scrollbar>
    </el-tab-pane>
    <el-tab-pane label="全局变量" name="globalVariable">
      <template #label>
        <el-tooltip content="全局变量" effect="dark" placement="right">
          <el-icon :size="20">
            <MostlyCloudy/>
          </el-icon>
        </el-tooltip>
      </template>
      <el-scrollbar height="calc(100vh - 112px)">
        <VariablePanel ref="VariablePanelRef"/>
      </el-scrollbar>
    </el-tab-pane>
    <el-tab-pane label="数据源" name="datasource">
      <template #label>
        <el-tooltip content="数据源" effect="dark" placement="right">
          <el-icon :size="20">
            <Coin/>
          </el-icon>
        </el-tooltip>
      </template>
      <el-scrollbar height="calc(100vh - 112px)">
        <DatasourcePanel :designer="designer"/>
      </el-scrollbar>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
import WidgetPanel from "./widget-panel/index.vue";
import VariablePanel from "./variable-panel/index.vue";
import DatasourcePanel from "./datasource-panel/index.vue";
import {Coin, DataBoard, MostlyCloudy, OfficeBuilding} from "@element-plus/icons-vue";

export default {
  name: "LeftPanel",
  components: {
    WidgetPanel,
    VariablePanel,
    DatasourcePanel,
    DataBoard,
    OfficeBuilding,
    MostlyCloudy,
    Coin
  },
  props: {
    designer: Object
  },
  data() {
    return {
      activeTab: ""
    };
  },
  methods: {
    handleTabClick(tab) {
      if (this.activeTab === tab.props.name) {
        this.activeTab = "";
      } else if ("globalVariable" === tab.props.name) {
        this.$refs.VariablePanelRef.refresh();
      }
    }
  }
};
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
</style>
