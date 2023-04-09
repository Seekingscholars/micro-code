<template>
  <el-dialog
    :draggable="true"
    :show-close="false"
    :title="title"
    append-to-body
    v-bind="$attrs"
    @open="onOpen"
  >
    <el-tabs
      v-model="activeTab"
      class="no-box-shadow no-padding"
      type="border-card"
    >
      <el-tab-pane label="请求配置" name="info">
        <el-form
          ref="form"
          :model="modelForm"
          :rules="rules"
          label-position="right"
          label-width="80px"
        >
          <el-form-item label="函数名称" prop="name">
            <el-input v-model="modelForm.name" />
          </el-form-item>
          <el-form-item label="请求地址" prop="url">
            <el-input v-model="modelForm.url" />
          </el-form-item>
          <el-form-item label="请求方法" prop="method">
            <el-select v-model="modelForm.method" style="width: 100%">
              <el-option
                v-for="item in requestMethod"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="超时时长" prop="timeout">
            <el-input v-model="modelForm.timeout" />
          </el-form-item>
          <el-form-item label="函数备注" prop="remark">
            <el-input v-model="modelForm.remark" />
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="请求头信息" name="header">
        <el-alert :closable="false" title="{" type="info" />
        <code-editor
          v-model="modelForm.header"
          :mode="'javascript'"
          :readonly="false"
        />
        <el-alert :closable="false" title="}" type="info" />
      </el-tab-pane>
      <el-tab-pane label="请求参数" name="param">
        <el-alert :closable="false" title="{" type="info" />
        <code-editor
          v-model="modelForm.param"
          :mode="'javascript'"
          :readonly="false"
        />
        <el-alert :closable="false" title="}" type="info" />
      </el-tab-pane>
      <el-tab-pane label="请求体" name="body">
        <el-alert :closable="false" title="{" type="info" />
        <code-editor
          v-model="modelForm.body"
          :mode="'javascript'"
          :readonly="false"
        />
        <el-alert :closable="false" title="}" type="info" />
      </el-tab-pane>
      <el-tab-pane label="请求后成功处理函数" name="success">
        <el-alert :closable="false" title="function(res){" type="info" />
        <code-editor
          v-model="modelForm.successFunction"
          :mode="'javascript'"
          :readonly="false"
        />
        <el-alert :closable="false" title="}" type="info" />
      </el-tab-pane>
      <el-tab-pane label="请求后失败处理函数" name="fail">
        <el-alert :closable="false" title="function(error){" type="info" />
        <code-editor
          v-model="modelForm.failFunction"
          :mode="'javascript'"
          :readonly="false"
        />
        <el-alert :closable="false" title="}" type="info" />
      </el-tab-pane>
    </el-tabs>
    <div slot="footer" class="dialog-footer">
      <el-button
        :loading="buttonLoading"
        plain
        type="primary"
        @click="submitForm"
        >测 试</el-button
      >
      <el-button :loading="buttonLoading" type="primary" @click="submitForm"
        >确 定</el-button
      >
      <el-button @click="cancel">取 消</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { addRestApi, REQUEST_METHOD, updateRestApi } from "./restApi";
import CodeEditor from "@/components/code-editor/index.vue";

export default {
  name: "RestApi",
  components: { CodeEditor },
  props: { datasource: Object },
  data() {
    return {
      buttonLoading: false,
      activeTab: "info",
      requestMethod: [],
      modelForm: {},
      rules: {
        name: [
          { required: true, message: "函数名称不能为空", trigger: "blur" }
        ],
        url: [{ required: true, message: "请求地址不能为空", trigger: "blur" }],
        method: [
          { required: true, message: "请求类型不能为空", trigger: "change" }
        ]
      }
    };
  },
  computed: {
    title() {
      return "创建REST API";
    }
  },
  mounted() {
    this.requestMethod = REQUEST_METHOD;
    this.reset();
  },
  methods: {
    // 表单重置
    reset() {
      this.modelForm = {
        id: undefined,
        name: undefined,
        url: undefined,
        method: "GET",
        timeout: "5000",
        remark: undefined,
        header: undefined,
        param: undefined,
        body: undefined,
        successFunction: undefined,
        failFunction: undefined
      };
    },
    onOpen() {
      if (this.datasource) {
        this.modelForm = Object.assign(this.modelForm, this.datasource);
      }
    },
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.buttonLoading = true;
          if (this.modelForm.id) {
            updateRestApi(this.modelForm)
              .then(res => {
                this.$modal.msgSuccess("修改成功");
                this.$emit("change");
                this.cancel();
              })
              .finally(() => {
                this.buttonLoading = false;
              });
          } else {
            addRestApi(this.modelForm)
              .then(res => {
                this.$modal.msgSuccess("新增成功");
                this.$emit("change");
                this.cancel();
              })
              .finally(() => {
                this.buttonLoading = false;
              });
          }
        }
      });
    },
    cancel() {
      this.reset();
      this.$emit("update:visible", false);
    }
  }
};
</script>

<style scoped></style>
