<template>
  <el-dialog
    :show-close="false"
    :title="title"
    v-bind="$attrs"
  >
    <el-form
      ref="form"
      :model="modelForm"
      :rules="rules"
      label-position="right"
      label-width="100px"
    >
      <el-form-item
        label="数据源名称"
        prop="name"
      >
        <el-input
          v-model="modelForm.name"
          placeholder="请输入数据源名称"
        />
      </el-form-item>
      <el-form-item
        label="数据源类型"
        prop="type"
      >
        <el-select
          v-model="modelForm.type"
          placeholder="请选择数据源类型"
          disabled
          style="width: 100%"
        >
          <el-option
            v-for="item in databaseList"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item
        label="数据源地址"
        prop="url"
      >
        <el-input
          v-model="modelForm.url"
          placeholder="请输入数据源地址"
        />
      </el-form-item>
      <el-form-item
        label="备注"
        prop="remark"
      >
        <el-input v-model="modelForm.remark" />
      </el-form-item>
      <div v-if="modelForm.type==='rest'">
        <el-form-item
          label="超时时长"
          prop="timeout"
        >
          <el-input v-model="modelForm.timeout" />
        </el-form-item>
        <el-form-item
          label="请求头"
          prop="header"
        >
          <div
            v-for="(item,index) in headers"
            class="header-setting"
          >
            <div class="header-setting-key">
              <el-input
                v-model="item.key"
                placeholder="请输入属性名"
              />
            </div>
            <div class="header-setting-value">
              <el-input
                v-model="item.value"
                placeholder="请输入属性值"
              />
            </div>
            <div>
              <el-button
                icon="el-icon-delete"
                type="text"
                title="删除"
                @click="removeHeader(index)"
              />
            </div>
          </div>
          <el-button
            type="text"
            icon="el-icon-plus"
            @click="addHeader"
          >添加</el-button>
        </el-form-item>
      </div>
      <div v-else>
        <el-form-item
          label="用户名"
          prop="username"
        >
          <el-input
            v-model="modelForm.username"
            placeholder="请输入用户名"
          />
        </el-form-item>
        <el-form-item
          label="密码"
          prop="password"
        >
          <el-input
            v-model="modelForm.password"
            show-password
            placeholder="请输入密码"
          />
        </el-form-item>
      </div>
    </el-form>

    <div
      slot="footer"
      class="dialog-footer"
    >
      <el-button
        type="primary"
        plain
        @click="testConnection"
      >测 试</el-button>
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
import datasourceApi from '@/api/datasource.api'
import { databaseList } from './db'
export default {
  name: 'DatabaseDialog',
  props: { row: Object },
  data() {
    return {
      buttonLoading: false,
      modelForm: {
        id: undefined,
        name: undefined,
        type: 'rest',
        url: undefined,
        timeout: '10000',
        header: undefined,
        username: undefined,
        password: undefined,
        remark: undefined
      },
      headers: [],
      rules: {
        name: [
          { required: true, message: '数据源名称不能为空', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '数据源不能为空', trigger: 'change' }
        ],
        url: [
          { required: true, message: '数据源地址不能为空', trigger: 'blur' }
        ],
        username: [
          { required: true, message: '用户名不能为空', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  computed: {
    title() {
      if (this.row) {
        return '编辑数据源'
      } else {
        return '创建数据源'
      }
    },
    databaseList() {
      return databaseList
    }
  },
  watch: {
    'modelForm.type': {
      handler(value) {
        if (value) {
          for (const databaseType of databaseList) {
            if (databaseType.value === value) {
              this.modelForm.url = databaseType.url
              break
            }
          }
        } else {
          this.modelForm.url = ''
        }
      },
      immediate: true
    }
  },
  mounted() {
    if (this.row) {
      this.get(this.row.id)
    }
  },
  methods: {
    async get(id) {
      const modelForm = await datasourceApi.get({ id })
      this.modelForm = Object.assign(this.modelForm, modelForm)
      if (this.modelForm.header) {
        this.headers = JSON.parse(this.modelForm.header)
      }
    },
    addHeader() {
      this.headers.push({ key: '', value: '' })
    },
    removeHeader(index) {
      this.headers.splice(index, 1)
    },
    submitForm() {
      this.$refs['form'].validate(async valid => {
        if (valid) {
          this.buttonLoading = true
          this.modelForm.header = JSON.stringify(this.headers)
          await datasourceApi.saveOrEdit(this.modelForm, !!this.modelForm.id)
          this.buttonLoading = false
          this.$emit('change')
          this.close()
        }
      })
    },
    testConnection() {
      this.$refs['form'].validate(async valid => {
        if (valid) {
          await datasourceApi.testConnection(this.modelForm)
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
.header-setting{
  display: flex;
  width: 100%;
  margin-bottom: 5px;
}
.header-setting-key{
  margin-right: 10px;
  flex: 1;
}
.header-setting-value{
  margin-right: 10px;
  flex: 4;
}
</style>
