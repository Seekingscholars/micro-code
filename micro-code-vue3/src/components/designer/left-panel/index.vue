<template>
  <div class="panel-container">
    <el-tabs
      v-model="firstTab"
      @tab-click="handleTabClick"
      class="no-bottom-margin indent-left-margin"
    >
      <el-tab-pane label="组件库" name="componentLib">
        <el-scrollbar height="calc(100vh - 112px)">
          <WidgetPanel :designer="designer" />
        </el-scrollbar>
      </el-tab-pane>
      <el-tab-pane label="全局变量" name="globalVariable">
        <el-scrollbar height="calc(100vh - 112px)">
          <VariablePanel ref="VariablePanelRef" />
        </el-scrollbar>
      </el-tab-pane>
      <el-tab-pane label="数据源" name="datasource">
        <el-scrollbar height="calc(100vh - 112px)">
          <DatasourcePanel :designer="designer" />
        </el-scrollbar>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import WidgetPanel from "./widget-panel/index.vue";
import VariablePanel from "./variable-panel/index.vue";
import DatasourcePanel from "./datasource-panel/index.vue";

export default {
  name: "LeftPanel",
  components: {
    WidgetPanel,
    VariablePanel,
    DatasourcePanel
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
      if ("globalVariable" === tab.name) {
        this.$refs.VariablePanelRef.refresh();
      }
    }
  }
};
</script>

<style lang="scss" scoped>
.panel-container {
  padding: 0 10px 10px 10px;
}
</style>
