<template>
  <el-dialog
    :show-close="false"
    :title="title"
    append-to-body
    v-bind="$attrs"
  >
    <el-tabs
      v-model="activeTab"
      class="no-box-shadow no-padding"
      type="border-card"
    >
      <el-tab-pane
        label="请求配置"
        name="info"
      >
        <el-form
          ref="form"
          :model="modelForm"
          :rules="rules"
          label-position="right"
          label-width="80px"
        >
          <el-form-item
            label="函数名称"
            prop="name"
          >
            <el-input v-model="modelForm.name" placeholder="请输入函数名称"/>
          </el-form-item>
          <el-form-item
            label="数据源"
            prop="datasourceId"
          >
            <el-select
              v-model="modelForm.datasourceId"
              style="width:100%"
              placeholder="请选择数据源"
            >
              <el-option
                v-for="item in datasoutceList"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
          <el-form-item
            label="请求地址"
            prop="url"
          >
            <el-input v-model="modelForm.url" placeholder="请输入请求地址，列如：/test"/>
          </el-form-item>
          <el-form-item
            label="请求方法"
            prop="method"
          >
            <el-select
              v-model="modelForm.method"
              style="width: 100%"
              placeholder="请选择请求方法"
            >
              <el-option
                v-for="item in requestMethod"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item
            label="超时时长"
            prop="timeout"
          >
            <el-input v-model="modelForm.timeout" placeholder="请输入超时时长，默认取数据源设置的超时时长"/>
          </el-form-item>
          <el-form-item
            label="函数备注"
            prop="remark"
          >
            <el-input v-model="modelForm.remark" placeholder="请输入函数备注"/>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane
        label="请求头信息"
        name="header"
      >
        <el-alert
          :closable="false"
          title="{"
          type="info"
        />
        <code-editor
          v-model="modelForm.header"
          :mode="'javascript'"
          :readonly="false"
        />
        <el-alert
          :closable="false"
          title="}"
          type="info"
        />
      </el-tab-pane>
      <el-tab-pane
        label="请求参数"
        name="param"
      >
        <el-alert
          :closable="false"
          title="{"
          type="info"
        />
        <code-editor
          v-model="modelForm.param"
          :mode="'javascript'"
          :readonly="false"
        />
        <el-alert
          :closable="false"
          title="}"
          type="info"
        />
      </el-tab-pane>
      <el-tab-pane
        label="请求体"
        name="body"
      >
        <el-alert
          :closable="false"
          title="{"
          type="info"
        />
        <code-editor
          v-model="modelForm.body"
          :mode="'javascript'"
          :readonly="false"
        />
        <el-alert
          :closable="false"
          title="}"
          type="info"
        />
      </el-tab-pane>
      <el-tab-pane
        label="请求后成功处理函数"
        name="success"
      >
        <el-alert
          :closable="false"
          title="function(res){"
          type="info"
        />
        <code-editor
          v-model="modelForm.successFunction"
          :mode="'javascript'"
          :readonly="false"
        />
        <el-alert
          :closable="false"
          title="}"
          type="info"
        />
      </el-tab-pane>
      <el-tab-pane
        label="请求后失败处理函数"
        name="fail"
      >
        <el-alert
          :closable="false"
          title="function(error){"
          type="info"
        />
        <code-editor
          v-model="modelForm.failFunction"
          :mode="'javascript'"
          :readonly="false"
        />
        <el-alert
          :closable="false"
          title="}"
          type="info"
        />
      </el-tab-pane>
    </el-tabs>
    <div
      slot="footer"
      class="dialog-footer"
    >
      <el-button
        :loading="buttonLoading"
        type="primary"
        @click="submitForm"
      >确 定</el-button>
      <el-button @click="close">取 消</el-button>
    </div>
  </el-dialog>
</template>

<script>
import restApi from '@/api/rest.api'
import datasourceApi from '@/api/datasource.api'
import CodeEditor from '@/components/code-editor/index'

export default {
  name: 'RestApiDialog',
  components: { CodeEditor },
  props: { api: Object },
  data() {
    return {
      buttonLoading: false,
      activeTab: 'info',
      datasoutceList: [],
      requestMethod: [{ label: 'GET', value: 'GET' }, { label: 'POST', value: 'POST' }, { label: 'PUT', value: 'PUT' }, { label: 'DELETE', value: 'DELETE' }],
      modelForm: {
        id: undefined,
        datasourceId: undefined,
        name: undefined,
        url: undefined,
        method: 'GET',
        timeout: undefined,
        remark: undefined,
        header: undefined,
        param: undefined,
        body: undefined,
        successFunction: undefined,
        failFunction: undefined
      },
      rules: {
        datasourceId: [
          { required: true, message: '数据源不能为空', trigger: 'change' }
        ],
        name: [
          { required: true, message: '函数名称不能为空', trigger: 'blur' }
        ],
        url: [
          { required: true, message: '请求地址不能为空', trigger: 'blur' }
        ],
        method: [
          { required: true, message: '请求类型不能为空', trigger: 'change' }
        ]
      }
    }
  },
  computed: {
    title() {
      if (this.modelForm.id) {
        return '编辑REST API'
      } else {
        return '创建REST API'
      }
    }
  },
  mounted() {
    if (this.api) {
      this.modelForm = Object.assign(this.modelForm, this.api)
    }
    this.listDatasource()
  },
  methods: {
    async listDatasource() {
      this.datasoutceList = await datasourceApi.list({})
    },
    submitForm() {
      this.$refs['form'].validate(async valid => {
        if (valid) {
          this.buttonLoading = true
          await restApi.saveOrEdit(this.modelForm, !!this.modelForm.id)
          this.buttonLoading = false
          this.$emit('change')
          this.close()
        }
      })
    },
    close() {
      this.$emit('update:visible', false)
    }
  }
}
</script>

<style scoped>

</style>
