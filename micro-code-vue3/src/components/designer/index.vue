<template>
  <el-container class="designer full-height">
    <el-header class="main-header">
      <div class="header">
        <div>
          <slot name="leftHeader"></slot>
        </div>
        <div>
          <el-tabs v-model="activeName">
            <el-tab-pane label="表单设计" name="form"></el-tab-pane>
            <el-tab-pane label="表单发布" name="publish"></el-tab-pane>
          </el-tabs>
        </div>
        <div>
          <slot name="rightHeader"></slot>
        </div>
      </div>
    </el-header>
    <el-container v-show="activeName==='form'">
      <el-aside class="left-panel">
        <left-panel :designer="designer"/>
      </el-aside>
      <el-container class="center-layout-container">
        <el-header class="toolbar-header">
          <toolbar-panel ref="toolbarRef" :designer="designer">
            <template v-for="(idx, slotName) in $slots" #[slotName]>
              <slot :name="slotName"></slot>
            </template>
          </toolbar-panel>
        </el-header>
        <el-main class="form-widget-main">
          <el-scrollbar :style="{height: scrollerHeight}">
            <v-form-widget v-if="flushed" ref="formRef" :designer="designer" :form-config="designer.formConfig">
            </v-form-widget>
          </el-scrollbar>
        </el-main>
      </el-container>
      <el-aside class="setting-panel">
        <setting-panel :designer="designer"/>
      </el-aside>
    </el-container>
  </el-container>
</template>

<script>
import LeftPanel from './left-panel/index.vue'
import ToolbarPanel from './toolbar-panel/index.vue'
import SettingPanel from './setting-panel/index.vue'
import VFormWidget from './form-widget/index.vue'
import { createDesigner } from './designer'
import {
  addWindowResizeHandler,
  deepClone,
  getAllContainerWidgets,
  getAllFieldWidgets,
  getQueryParam
} from '@/utils/util'



export default {
  name: 'VFormDesigner',
  componentName: 'VFormDesigner',

  components: {
    LeftPanel,
    ToolbarPanel,
    SettingPanel,
    VFormWidget
  },
  props: {
    /* 禁止显示的组件名称数组 */
    bannedWidgets: {
      type: Array,
      default: () => []
    },

    designerConfig: {
      type: Object,
      default: () => {
        return {}
      }
    }

  },
  data() {
    return {
      activeName: 'form',
      vsCodeFlag: false,
      caseName: '',
      flushed: true,
      scrollerHeight: 0,
      designer: createDesigner(this),

      fieldList: [],
      dataModel: {}
    }
  },
  provide() {
    return {
      serverFieldList: this.fieldList,
      getDesignerConfig: () => this.designerConfig,
      getBannedWidgets: () => this.bannedWidgets,
      $model:this.dataModel
    }
  },
  created() {
    this.vsCodeFlag = getQueryParam('vscode') == 1
    this.caseName = getQueryParam('case')
  },
  mounted() {
    this.scrollerHeight = window.innerHeight - 56 - 36 + 'px'
    addWindowResizeHandler(() => {
      this.$nextTick(() => {
        this.scrollerHeight = window.innerHeight - 56 - 36 + 'px'
      })
    })
  },
  methods: {
    showLink(configName) {
      if (this.designerConfig[configName] === undefined) {
        return true
      }

      return !!this.designerConfig[configName]
    },

    setFormJson(formJson) {
      let modifiedFlag = false
      if (!!formJson) {
        if (typeof formJson === 'string') {
          modifiedFlag = this.designer.loadFormJson(JSON.parse(formJson))
        } else if (formJson.constructor === Object) {
          modifiedFlag = this.designer.loadFormJson(formJson)
        }
        if (modifiedFlag) {
          this.refreshDesigner()
        }
      }
    },

    getFormJson() {
      return {
        widgetList: deepClone(this.designer.widgetList),
        formConfig: deepClone(this.designer.formConfig)
      }
    },

    clearDesigner() {
      this.$refs.toolbarRef.clearFormWidget()
    },

    /**
     * 刷新表单设计器
     */
    refreshDesigner() {
      this.flushed = false
      this.$nextTick(() => setTimeout(() => this.flushed = true, 100))
    },

    /**
     * 预览表单
     */
    previewForm() {
      this.$refs.toolbarRef.previewForm()
    },
    /**
     * 获取所有字段组件
     * @returns {*[]}
     */
    getFieldWidgets(widgetList = null) {
      return !!widgetList ? getAllFieldWidgets(widgetList) : getAllFieldWidgets(this.designer.widgetList)
    },

    /**
     * 获取所有容器组件
     * @returns {*[]}
     */
    getContainerWidgets(widgetList = null) {
      return !!widgetList ? getAllContainerWidgets(widgetList) : getAllContainerWidgets(this.designer.widgetList)
    },

    getWidgetRef(widgetName, showError = false) {
      return this.$refs['formRef'].getWidgetRef(widgetName, showError)
    },

    getSelectedWidgetRef() {
      return this.$refs['formRef'].getSelectedWidgetRef()
    }

    //TODO: 增加更多方法！！

  }
}
</script>

<style lang="scss" scoped>
.el-container.designer {
  background: #fff;

  ::v-deep aside { /* 防止aside样式被外部样式覆盖！！ */
    margin: 0;
    padding: 0;
    background: inherit;
  }
}

.el-container.full-height {
  height: 100%;
  overflow-y: hidden;
}

.el-container.center-layout-container {
  min-width: 680px;
  border-left: 2px dotted #EBEEF5;
  border-right: 2px dotted #EBEEF5;
}

.el-header.main-header {
  border-bottom: 2px dotted #EBEEF5;
  height: 48px !important;
  line-height: 48px !important;
  min-width: 800px;
}

.header {
  display: flex;
  justify-content: space-between;
}

.float-left {
  float: left;
}

.float-right {
  float: right;
}

.el-dropdown-link {
  margin-right: 12px;
  cursor: pointer;
}

div.external-link a {
  font-size: 13px;
  text-decoration: none;
  margin-right: 10px;
  color: #606266;
}

.el-header.toolbar-header {
  font-size: 14px;
  border-bottom: 1px dotted #CCCCCC;
  height: 42px !important;
  //line-height: 42px !important;
}

.el-aside.left-panel {
  width: 280px !important;
}

.el-aside.setting-panel {
  width: 400px !important;
}

.el-main.form-widget-main {
  padding: 0;
}
</style>
