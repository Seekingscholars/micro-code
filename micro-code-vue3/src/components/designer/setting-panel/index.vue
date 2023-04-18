<template>
  <div class="panel-container">
    <el-tabs v-model="activeTab">
      <el-tab-pane label="组件设置" name="1">
        <el-scrollbar height="calc(100vh - 112px)">
          <template v-if="!!designer.selectedWidget">
            <el-form
              class="setting-form"
              label-position="left"
              label-width="120px"
              size="small"
            >
              <el-collapse
                v-model="widgetActiveCollapseNames"
                class="setting-collapse"
              >
                <template v-for="item in collapseList" :key="item.type">
                  <el-collapse-item
                    v-if="designer.selectedWidget[item.type]"
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
                        :option-model="designer.selectedWidget[item.type]"
                      />
                    </template>
                  </el-collapse-item>
                </template>
              </el-collapse>
            </el-form>
          </template>
        </el-scrollbar>
      </el-tab-pane>

      <el-tab-pane v-if="!!designer" label="表单设置" name="2">
        <el-scrollbar height="calc(100vh - 112px)">
          <form-setting
            :designer="designer"
            :form-config="designer.formConfig"
          />
        </el-scrollbar>
      </el-tab-pane>
    </el-tabs>

    <el-dialog
      v-model="showWidgetEventDialogFlag"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :destroy-on-close="true"
      :draggable="true"
      :show-close="true"
      append-to-body
      class="small-padding-dialog"
      title="组件事件处理"
    >
      <el-alert :closable="false" :title="eventHeader" type="info"/>
      <code-editor
        ref="ecEditor"
        v-model="eventHandlerCode"
        :mode="'javascript'"
        :readonly="false"
      />
      <el-alert :closable="false" title="}" type="info"/>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="showWidgetEventDialogFlag = false"> 取消</el-button>
          <el-button type="primary" @click="saveEventHandler"> 确定</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import CodeEditor from "@/components/code-editor/index.vue";
import PropertyEditors from "./property-editor";
import FormSetting from "./form-setting.vue";
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
  padding: 0px 10px 0 10px;
  width: 400px;
}

.setting-form {
  padding-right: 10px;
}
</style>
