<template>
  <div class="panel-container">
    <el-tabs
      :active-name="activeTab"
      style="height: 100%;"
    >
      <el-tab-pane
        label="组件设置"
        name="1"
      >
        <el-scrollbar class="scrollbar">
          <template v-if="!!designer.selectedWidget">
            <el-form
              class="setting-form"
              label-position="left"
              label-width="120px"
              size="mini"
              @submit.native.prevent
            >
              <FormEditor :designer="designer" />
            </el-form>
          </template>
        </el-scrollbar>
      </el-tab-pane>

      <el-tab-pane
        v-if="!!designer"
        label="表单设置"
        name="2"
      >
        <el-scrollbar class="scrollbar">
          <form-setting
            :designer="designer"
            :form-config="designer.formConfig"
          />
        </el-scrollbar>
      </el-tab-pane>
    </el-tabs>

    <el-dialog
      v-if="showWidgetEventDialogFlag"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :destroy-on-close="true"
      :show-close="true"
      :visible.sync="showWidgetEventDialogFlag"
      append-to-body
      class="small-padding-dialog"
      title="组件事件处理"
    >
      <el-alert
        :closable="false"
        :title="eventHeader"
        type="info"
      />
      <code-editor
        ref="ecEditor"
        v-model="eventHandlerCode"
        :mode="'javascript'"
        :readonly="false"
      />
      <el-alert
        :closable="false"
        title="}"
        type="info"
      />
      <div
        slot="footer"
        class="dialog-footer"
      >
        <el-button @click="showWidgetEventDialogFlag = false">
          取消
        </el-button>
        <el-button
          type="primary"
          @click="saveEventHandler"
        >
          确定
        </el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import CodeEditor from '@/components/code-editor/index'
import FormSetting from './form-setting'
import FormEditor from './form-editor'

export default {
  name: 'SettingPanel',
  componentName: 'SettingPanel',
  components: {
    CodeEditor,
    FormSetting,
    FormEditor
  },
  props: {
    designer: Object
  },
  data() {
    return {
      activeTab: '2',
      showWidgetEventDialogFlag: false,
      eventHandlerCode: '',
      curEventName: '',
      eventHeader: ''
    }
  },
  computed: {
    optionModel: {
      get() {
        return this.designer.selectedWidget.options
      },

      set(newValue) {
        this.designer.selectedWidget.options = newValue
      }
    }

  },
  watch: {
    'designer.selectedWidget': {
      handler(val) {
        if (val) {
          this.activeTab = '1'
        }
      }
    }
  },
  created() {
    this.$on('editEventHandler', function(eventName, eventParams) {
      this.editEventHandler(eventName, eventParams)
    })
  },
  mounted() {
    if (!this.designer.selectedWidget) {
      this.activeTab = '2'
    } else {
      this.activeTab = '1'
    }
  },
  methods: {
    editEventHandler(eventName, eventParams) {
      this.curEventName = eventName
      this.eventHeader = `${this.optionModel.name}.${eventName}(${eventParams.join(', ')}) {`
      this.eventHandlerCode = this.designer.selectedWidget.events[eventName] || ''

      // 设置字段校验函数示例代码
      if ((eventName === 'onValidate') && (!this.optionModel['onValidate'])) {
        this.eventHandlerCode = '  /* sample code */\n  /*\n  if ((value > 100) || (value < 0)) {\n    callback(new Error(\'error message\'))  //fail\n  } else {\n    callback();  //pass\n  }\n  */'
      }

      this.showWidgetEventDialogFlag = true
    },

    saveEventHandler() {
      this.designer.selectedWidget.events[this.curEventName] = this.eventHandlerCode
      this.showWidgetEventDialogFlag = false
    }

  }
}
</script>

<style lang="scss" scoped>
.panel-container {
  padding: 0px 10px 0 10px;
  width: 400px;
}
.scrollbar{
height: calc(100vh - 102px)
}
.setting-form {
  padding-right: 10px;
}
</style>
