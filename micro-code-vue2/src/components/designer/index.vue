<template>
  <div class="design-container">
    <div class="design-header">
      <slot name="header" />
    </div>
    <div
      v-if="flushed"
      class="design-body"
    >
      <left-panel
        :designer="designer"
        class="design-body-left"
      >
        <template #left="{designer}">
          <slot
            name="left"
            :designer="designer"
          />
        </template>
      </left-panel>
      <el-scrollbar class="design-body-center scrollbar">
        <DesignWidget
          ref="formRef"
          :designer="designer"
          :form-config="designer.formConfig"
        />
      </el-scrollbar>
      <setting-panel
        :designer="designer"
        class="design-body-right"
      />
    </div>
  </div>
</template>

<script>
import LeftPanel from './left-panel/index.vue'
import SettingPanel from './setting-panel/index.vue'
import DesignWidget from './form-widget/index.vue'
import { createDesigner } from './designer'

export default {
  name: 'FormDesigner',
  components: {
    LeftPanel,
    SettingPanel,
    DesignWidget
  },
  provide() {
    return {
      $model: this.dataModel
    }
  },
  props: {
    dataModel: {
      required: false,
      default: {}
    }
  },
  data() {
    return {
      flushed: true,
      scrollerHeight: 0,
      designer: createDesigner(this)
    }
  },
  mounted() {
    this.scrollerHeight = window.innerHeight - 56 - 36 + 'px'
  },
  methods: {
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
    /**
     * 刷新表单设计器
     */
    refreshDesigner() {
      this.flushed = false
      this.$nextTick(() => setTimeout(() => (this.flushed = true), 100))
    }

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
.scrollbar{
  height: calc(100vh - 48px);
}
</style>
