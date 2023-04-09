<template>
  <div class="panel-container">
    <el-tabs :active-name="activeTab" style="height: 100%; overflow: hidden">
      <el-tab-pane label="组件设置" name="1">
        <el-scrollbar :style="{ height: scrollerHeight }">
          <template v-if="!!designer.selectedWidget">
            <el-form
              class="setting-form"
              label-position="left"
              label-width="120px"
              size="mini"
              @submit.native.prevent
            >
              <el-collapse
                v-model="widgetActiveCollapseNames"
                class="setting-collapse"
              >
                <el-collapse-item
                  v-for="item in collapseList"
                  v-if="designer.selectedWidget[item.type]"
                  :key="item.type"
                  :name="item.type"
                  :title="item.name"
                >
                  <template
                    v-for="propName in Object.keys(
                      designer.selectedWidget[item.type]
                    )"
                  >
                    <component
                      :is="getPropEditor(propName)"
                      v-if="getPropEditor(propName) != null"
                      :designer="designer"
                      :option-model="designer.selectedWidget[item.type]"
                      :selected-widget="designer.selectedWidget"
                    />
                  </template>
                </el-collapse-item>
              </el-collapse>
            </el-form>
          </template>
        </el-scrollbar>
      </el-tab-pane>

      <el-tab-pane v-if="!!designer" label="表单设置" name="2">
        <el-scrollbar :style="{ height: scrollerHeight }">
          <form-setting
            :designer="designer"
            :form-config="designer.formConfig"
          />
        </el-scrollbar>
      </el-tab-pane>
    </el-tabs>

    <el-dialog
      v-if="showWidgetEventDialogFlag"
      v-dialog-drag
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :destroy-on-close="true"
      :show-close="true"
      title="组件事件处理"
      v-model:visible="showWidgetEventDialogFlag"
      append-to-body
      class="small-padding-dialog"
    >
      <el-alert :closable="false" :title="eventHeader" type="info" />
      <code-editor
        ref="ecEditor"
        v-model="eventHandlerCode"
        :mode="'javascript'"
        :readonly="false"
      />
      <el-alert :closable="false" title="}" type="info" />
      <div slot="footer" class="dialog-footer">
        <el-button @click="showWidgetEventDialogFlag = false"> 取消 </el-button>
        <el-button type="primary" @click="saveEventHandler"> 确定 </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import CodeEditor from "@/components/code-editor/index.vue";
import PropertyEditors from "./property-editor";
import FormSetting from "./form-setting.vue";
import { addWindowResizeHandler } from "@/utils/util";
import collapseJson from "./collapse.json";

export default {
  name: "SettingPanel",
  componentName: "SettingPanel",
  components: {
    CodeEditor,
    FormSetting,
    ...PropertyEditors
  },
  props: {
    designer: Object
  },
  data() {
    return {
      scrollerHeight: 0,
      activeTab: "2",
      widgetActiveCollapseNames: [],
      collapseList: [],
      showWidgetEventDialogFlag: false,
      eventHandlerCode: "",
      curEventName: "",
      eventHeader: ""
    };
  },
  computed: {
    optionModel: {
      get() {
        return this.designer.selectedWidget.options;
      },

      set(newValue) {
        this.designer.selectedWidget.options = newValue;
      }
    }
  },
  watch: {
    "designer.selectedWidget": {
      handler(val) {
        if (!!val) {
          this.activeTab = "1";
        }
      }
    }
  },
  created() {
    // this.$on("editEventHandler", function (eventName, eventParams) {
    //   this.editEventHandler(eventName, eventParams);
    // });
  },
  mounted() {
    this.collapseList = collapseJson;
    this.widgetActiveCollapseNames = collapseJson.map(
      collapse => collapse.type
    );
    if (!this.designer.selectedWidget) {
      this.activeTab = "2";
    } else {
      this.activeTab = "1";
    }

    this.scrollerHeight = window.innerHeight - 56 - 48 + "px";
    addWindowResizeHandler(() => {
      this.$nextTick(() => {
        this.scrollerHeight = window.innerHeight - 56 - 48 + "px";
        //console.log(this.scrollerHeight)
      });
    });
  },
  methods: {
    getPropEditor(propName) {
      const editorName = `${propName}-editor`;
      let ownPropEditorName = `${this.designer.selectedWidget.type}-${editorName}`;
      if (!!this.$options.components[ownPropEditorName]) {
        //局部注册的属性编辑器组件
        return ownPropEditorName;
      }
      if (!!this.$options.components[editorName]) {
        //局部注册的属性编辑器组件
        return editorName;
      }
      return null;
    },

    editEventHandler(eventName, eventParams) {
      this.curEventName = eventName;
      this.eventHeader = `${
        this.optionModel.name
      }.${eventName}(${eventParams.join(", ")}) {`;
      this.eventHandlerCode =
        this.designer.selectedWidget.events[eventName] || "";

      // 设置字段校验函数示例代码
      if (eventName === "onValidate" && !this.optionModel["onValidate"]) {
        this.eventHandlerCode =
          "  /* sample code */\n  /*\n  if ((value > 100) || (value < 0)) {\n    callback(new Error('error message'))  //fail\n  } else {\n    callback();  //pass\n  }\n  */";
      }

      this.showWidgetEventDialogFlag = true;
    },

    saveEventHandler() {
      this.designer.selectedWidget.events[this.curEventName] =
        this.eventHandlerCode;
      this.showWidgetEventDialogFlag = false;
    }
  }
};
</script>

<style lang="scss" scoped>
.panel-container {
  padding: 0 10px;
}

.setting-collapse {
  ::v-deep .el-collapse-item__content {
    padding-bottom: 6px;
  }

  ::v-deep .el-collapse-item__header {
    font-style: italic;
    font-weight: bold;
  }
}

.setting-form {
  ::v-deep .el-form-item__label {
    font-size: 13px;
    //text-overflow: ellipsis;
    overflow: hidden;
    white-space: nowrap;
  }

  ::v-deep .el-form-item--mini.el-form-item {
    margin-bottom: 6px;
  }
}

/* 隐藏Chrome浏览器中el-input数字输入框右侧的上下调整小箭头 */
::v-deep .hide-spin-button input::-webkit-outer-spin-button,
::v-deep .hide-spin-button input::-webkit-inner-spin-button {
  -webkit-appearance: none !important;
}

/* 隐藏Firefox浏览器中el-input数字输入框右侧的上下调整小箭头 */
::v-deep .hide-spin-button input[type="number"] {
  -moz-appearance: textfield;
}

::v-deep .custom-divider.el-divider--horizontal {
  margin: 10px 0;
}

::v-deep .custom-divider-margin-top.el-divider--horizontal {
  margin: 20px 0;
}

.small-padding-dialog {
  ::v-deep .el-dialog__body {
    padding: 6px 15px 12px 15px;
  }
}
</style>
