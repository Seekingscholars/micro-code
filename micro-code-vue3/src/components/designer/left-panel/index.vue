<template>
  <el-scrollbar class="side-scroll-bar">
    <div class="panel-container">
      <el-tabs v-model="firstTab" @tab-click="handleTabClick" class="no-bottom-margin indent-left-margin">
        <el-tab-pane label="组件库" name="componentLib">
          <el-scrollbar :style="{height: scrollerHeight}">
            <WidgetPanel :designer="designer"></WidgetPanel>
          </el-scrollbar>
        </el-tab-pane>
        <el-tab-pane label="全局变量" name="globalVariable">
          <el-scrollbar :style="{height: scrollerHeight}">
            <VariablePanel ref="VariablePanelRef"></VariablePanel>
          </el-scrollbar>
        </el-tab-pane>
        <el-tab-pane label="数据源" name="datasource">
          <el-scrollbar :style="{height: scrollerHeight}">
            <DatasourcePanel :designer="designer"></DatasourcePanel>
          </el-scrollbar>
        </el-tab-pane>
      </el-tabs>
    </div>
  </el-scrollbar>
</template>

<script>
import { addWindowResizeHandler } from '@/utils/util'
import WidgetPanel from './widget-panel/index.vue'
import VariablePanel from './variable-panel/index.vue'
import DatasourcePanel from './datasource-panel/index.vue'

export default {
  name: 'left-panel',
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
      firstTab: 'componentLib',
      scrollerHeight: 0
    }
  },
  computed: {
    //
  },
  mounted() {
    this.scrollerHeight = window.innerHeight - 56 - 48 + 'px'
    addWindowResizeHandler(() => {
      this.$nextTick(() => {
        this.scrollerHeight = window.innerHeight - 56 - 48 + 'px'
      })
    })
  },
  methods:{
    handleTabClick(tab){
      if('globalVariable'===tab.name){
        this.$refs.VariablePanelRef.refresh()
      }
    }
  }

}
</script>

<style lang="scss" scoped>

.side-scroll-bar {
  ::v-deep .el-scrollbar__wrap {
    overflow-x: hidden;
  }
}

div.panel-container {
  padding: 0 10px 10px 10px;
}

</style>
