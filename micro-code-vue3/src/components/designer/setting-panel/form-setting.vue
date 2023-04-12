<template>
  <div>
    <el-form
      :model="designer.formConfig"
      class="setting-form"
      label-position="left"
      label-width="120px"
      size="small"
      @submit.native.prevent
    >
      <el-collapse v-model="formActiveCollapseNames" class="setting-collapse">
        <el-collapse-item name="1" title="基本属性">
          <el-form-item label="组件大小">
            <el-select v-model="designer.formConfig.size" style="width: 100%">
              <el-option
                v-for="item in formSizes"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="标签宽度">
            <el-input
              v-model="designer.formConfig.labelWidth"
              :min="0"
              style="width: 100%"
              type="number"
            />
          </el-form-item>
          <el-form-item label="对齐方式">
            <el-radio-group
              v-model="designer.formConfig.labelPosition"
              class="radio-group-custom"
            >
              <el-radio-button label="left">左对齐</el-radio-button>
              <el-radio-button label="right">右对齐</el-radio-button>
              <el-radio-button label="top">顶部对齐</el-radio-button>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="表单禁用">
            <el-switch v-model="designer.formConfig.disabled" />
          </el-form-item>
          <el-form-item label="全局数据">
            <el-button
              icon="el-icon-edit"
              plain
              round
              type="info"
              @click="editGlobalData"
              >编写代码</el-button
            >
          </el-form-item>
          <el-form-item label="全局函数">
            <el-button
              icon="el-icon-edit"
              plain
              round
              type="info"
              @click="editGlobalFunction"
              >编写代码</el-button
            >
          </el-form-item>
        </el-collapse-item>

        <el-collapse-item name="2" title="事件属性">
          <el-form-item label="onCreated" label-width="120px">
            <el-button
              icon="el-icon-edit"
              plain
              round
              type="info"
              @click="editFormEventHandler('onCreated')"
            >
              编写代码
            </el-button>
          </el-form-item>
          <el-form-item label="onMounted" label-width="120px">
            <el-button
              icon="el-icon-edit"
              plain
              round
              type="info"
              @click="editFormEventHandler('onMounted')"
            >
              编写代码
            </el-button>
          </el-form-item>
        </el-collapse-item>
      </el-collapse>
    </el-form>

    <el-dialog
      v-model="showFormEventDialogFlag"
      :draggable="true"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :destroy-on-close="true"
      :show-close="true"
      class="small-padding-dialog"
      title="表单事件处理"
    >
      <el-alert
        :closable="false"
        :title="'form.' + eventParamsMap[curEventName]"
        type="info"
      />
      <code-editor
        ref="ecEditor"
        v-model="formEventHandlerCode"
        :mode="'javascript'"
        :readonly="false"
      />
      <el-alert :closable="false" title="}" type="info" />
      <template #footer>
      <div class="dialog-footer">
        <el-button @click="showFormEventDialogFlag = false"> 取消 </el-button>
        <el-button type="primary" @click="saveFormEventHandler">
          确定
        </el-button>
      </div>
      </template>
    </el-dialog>

    <el-dialog
      v-model="showEditFunctionDialogFlag"
      :draggable="true"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :destroy-on-close="true"
      :show-close="true"
      append-to-body
      class="small-padding-dialog"
      title="全局函数"
    >
      <code-editor
        ref="gfEditor"
        v-model="functionCode"
        :mode="'javascript'"
        :readonly="false"
      />
      <template #footer>
      <div class="dialog-footer">
        <el-button @click="showEditFunctionDialogFlag = false">
          取消
        </el-button>
        <el-button type="primary" @click="saveGlobalFunction"> 确定 </el-button>
      </div>
      </template>
    </el-dialog>
    <el-dialog
      v-model="showEditDataDialogFlag"
      :draggable="true"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :destroy-on-close="true"
      :show-close="true"
      append-to-body
      class="small-padding-dialog"
      title="全局数据"
    >
      <el-alert :closable="false" title="{" type="info" />
      <code-editor
        ref="gfEditor"
        v-model="dataCode"
        :mode="'javascript'"
        :readonly="false"
      />
      <el-alert :closable="false" title="}" type="info" />
      <template #footer>
      <div  class="dialog-footer">
        <el-button @click="showEditDataDialogFlag = false"> 取消 </el-button>
        <el-button type="primary" @click="saveGlobalData"> 确定 </el-button>
      </div>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import CodeEditor from "@/components/code-editor/index.vue";

export default {
  name: "FormSetting",
  components: {
    CodeEditor
  },
  props: {
    designer: Object
  },
  data() {
    return {
      formActiveCollapseNames: ["1", "2"],
      formSizes: [
        { label: "default", value: "" },
        { label: "large", value: "large" },
        { label: "small", value: "small" }
      ],
      showEditFunctionDialogFlag: false,
      showEditDataDialogFlag: false,
      dataCode: "",
      functionCode: "",

      showFormEventDialogFlag: false,
      formEventHandlerCode: "",
      curEventName: "",

      eventParamsMap: {
        onCreated: "onCreated() {",
        onMounted: "onMounted() {"
      }
    };
  },
  methods: {
    editGlobalData() {
      this.dataCode = this.designer.formConfig.dataCode;
      this.showEditDataDialogFlag = true;
    },
    editGlobalFunction() {
      this.functionCode = this.designer.formConfig.functionCode;
      this.showEditFunctionDialogFlag = true;
    },

    saveGlobalFunction() {
      this.designer.formConfig.functionCode = this.functionCode;
      console.log(this.designer.formConfig.functionCode);
      this.showEditFunctionDialogFlag = false;
    },

    saveGlobalData() {
      this.designer.formConfig.dataCode = this.dataCode;
      this.showEditDataDialogFlag = false;
    },

    editFormEventHandler(eventName) {
      this.curEventName = eventName;
      this.formEventHandlerCode = this.designer.formConfig[eventName];
      this.showFormEventDialogFlag = true;
    },

    saveFormEventHandler() {
      this.designer.formConfig[this.curEventName] = this.formEventHandlerCode;
      this.showFormEventDialogFlag = false;
    }
  }
};
</script>

<style lang="scss" scoped>
.setting-form {
  padding-right: 10px;
}
</style>
