<template>
  <el-tabs
    v-model="firstTab"
    tab-position="left"
    @tab-click="handleTabClick"
    :class="firstTab ? 'showTab' : 'hideTab'"
  >
    <el-tab-pane label="组件库" name="componentLib">
      <template #label>
        <el-tooltip effect="dark" content="组件库" placement="right">
          <el-icon :size="20"><OfficeBuilding /></el-icon>
        </el-tooltip>
      </template>
      <el-scrollbar height="calc(100vh - 112px)">
        <WidgetPanel :designer="designer" />
      </el-scrollbar>
    </el-tab-pane>
    <el-tab-pane label="全局变量" name="globalVariable">
      <template #label>
        <el-tooltip effect="dark" content="全局变量" placement="right">
          <el-icon :size="20"><MostlyCloudy /></el-icon>
        </el-tooltip>
      </template>
      <el-scrollbar height="calc(100vh - 112px)">
        <VariablePanel ref="VariablePanelRef" />
      </el-scrollbar>
    </el-tab-pane>
    <el-tab-pane label="数据源" name="datasource">
      <template #label>
        <el-tooltip effect="dark" content="数据源" placement="right">
          <el-icon :size="20"><Coin /></el-icon>
        </el-tooltip>
      </template>
      <el-scrollbar height="calc(100vh - 112px)">
        <DatasourcePanel :designer="designer" />
      </el-scrollbar>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
import WidgetPanel from "./widget-panel/index.vue";
import VariablePanel from "./variable-panel/index.vue";
import DatasourcePanel from "./datasource-panel/index.vue";
import { OfficeBuilding, MostlyCloudy, Coin } from "@element-plus/icons-vue";
export default {
  name: "LeftPanel",
  components: {
    WidgetPanel,
    VariablePanel,
    DatasourcePanel,
    OfficeBuilding,
    MostlyCloudy,
    Coin
  },
  props: {
    designer: Object
  },
  data() {
    return {
      firstTab: "componentLib"
    };
  },
  methods: {
    handleTabClick(tab) {
      if (this.firstTab === tab.props.name) {
        this.firstTab = "";
      } else if ("globalVariable" === tab.name) {
        this.$refs.VariablePanelRef.refresh();
      }
    }
  }
};
</script>
<style scoped>
.showTab{
  height: 100%;
  width: 340px !important;
}
.hideTab{
  height: 100%;
  width: 60px !important;
}
</style>
