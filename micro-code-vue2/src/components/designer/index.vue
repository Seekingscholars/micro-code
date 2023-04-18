<template>
  <div class="design-container">
    <div class="design-header">
      <slot name="header"/>
    </div>
    <div class="design-body">
      <left-panel :designer="designer" class="design-body-left"/>
      <el-scrollbar class="design-body-center" height="calc(100vh - 112px)">
        <v-form-widget
          v-if="flushed"
          ref="formRef"
          :designer="designer"
          :form-config="designer.formConfig"
        />
      </el-scrollbar>
      <setting-panel :designer="designer" class="design-body-right"/>
    </div>
  </div>
</template>

<script>
import LeftPanel from './left-panel/index.vue'
import SettingPanel from './setting-panel/index.vue'
import VFormWidget from './form-widget/index.vue'
import {createDesigner} from './designer'
import {addWindowResizeHandler, getAllContainerWidgets, getAllFieldWidgets, getQueryParam} from '@/utils/util'

export default {
  name: 'FormDesigner',
  componentName: 'FormDesigner',

  components: {
    LeftPanel,
    SettingPanel,
    VFormWidget
  },
  provide() {
    return {
      serverFieldList: this.fieldList,
      getDesignerConfig: () => this.designerConfig,
      getBannedWidgets: () => this.bannedWidgets,
      $model: this.dataModel
    }
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
      vsCodeFlag: false,
      caseName: '',
      flushed: true,
      scrollerHeight: 0,
      designer: createDesigner(this),

      fieldList: [],
      dataModel: {}
    }
  },
  created() {
    this.vsCodeFlag = getQueryParam('vscode') === 1
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
      if (formJson) {
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
        widgetList: this.designer.widgetList,
        formConfig: this.designer.formConfig
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
      this.$nextTick(() => setTimeout(() => (this.flushed = true), 100))
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
      return widgetList
        ? getAllFieldWidgets(widgetList)
        : getAllFieldWidgets(this.designer.widgetList)
    },

    /**
     * 获取所有容器组件
     * @returns {*[]}
     */
    getContainerWidgets(widgetList = null) {
      return widgetList
        ? getAllContainerWidgets(widgetList)
        : getAllContainerWidgets(this.designer.widgetList)
    },

    getWidgetRef(widgetName, showError = false) {
      return this.$refs['formRef'].getWidgetRef(widgetName, showError)
    },

    getSelectedWidgetRef() {
      return this.$refs['formRef'].getSelectedWidgetRef()
    }

    // TODO: 增加更多方法！！
  }
}
</script>

<style lang="scss" scoped>
.design-container {
  height: 100%;
}

.design-header {
  width: 100%;
  height: 48px !important;
  overflow: hidden;
  border-bottom: 1px dotted #cccccc;
}

.design-body {
  display: flex;
  height: calc(100% - 48px);
}

.design-body-center {
  flex: 1;
  border-left: 1px dotted #cccccc;
  border-right: 1px dotted #cccccc;
}
</style>
