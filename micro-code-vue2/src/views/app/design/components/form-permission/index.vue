<template>
  <el-dialog :show-close="true" :title="title" v-bind="$attrs" width="800px" :before-close="close">
    <el-form ref="form" :model="modelForm" :rules="rules" label-position="right" label-width="80px">
      <el-form-item label="是否分享" prop="open">
        <el-switch
          v-model="modelForm.open"
        />
      </el-form-item>
      <el-form-item label="分享方式" prop="shareType">
        <el-radio-group v-model="modelForm.shareType">
          <el-radio label="publish">公开</el-radio>
          <el-radio label="password">密码</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="提取密码" prop="password">
        <el-input v-model="modelForm.password" :disabled="modelForm.shareType!=='password'" placeholder="系统自动生成" />
      </el-form-item>
      <el-form-item label="有效时间" prop="expireTime">
        <el-date-picker v-model="modelForm.expireTime" style="width: 100%" type="daterange" value-format="yyyy-MM-dd HH:mm:ss" />
      </el-form-item>
      <el-form-item label="访问链接" prop="url">
        <el-row>
          <el-col :span="18">
            <el-input :value="url" disabled placeholder="系统自动生成" />
          </el-col>
          <el-col v-if="!!url" :span="6">
            <div style="margin-left: 10px">
              <el-button @click="copyUrl">复制</el-button>
              <el-button @click="openUrl">打开</el-button>
            </div>
          </el-col>
        </el-row>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="close">取消</el-button>
      <el-button :loading="buttonLoading" type="primary" @click="submitForm">确定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import formApi from '@/api/form'
import { randomAlphaNumber } from '@/utils/randomUtil'

export default {
  name: 'FormPermission',
  props: {
    form: Object
  },
  data() {
    return {
      buttonLoading: false,
      activeTab: 'info',
      requestMethod: [{ label: 'GET', value: 'GET' }, { label: 'POST', value: 'POST' }, {
        label: 'PUT',
        value: 'PUT'
      }, { label: 'DELETE', value: 'DELETE' }],
      modelForm: {
        id: undefined,
        open: false,
        expireTime: undefined,
        startExpireTime: undefined,
        endExpireTime: undefined,
        password: undefined,
        shareType: 'publish'
      },
      rules: {}
    }
  },
  computed: {
    title() {
      return '表单分享设置'
    },
    url() {
      const to = this.$router.resolve({ path: '/app/open/' + this.modelForm.id })
      return location.origin + '/' + to.href
    }
  },
  created() {
    this.getPermission(this.form.id)
  },
  methods: {
    async getPermission(id) {
      const modelForm = await formApi.getPermission({ id })
      this.modelForm = Object.assign(this.modelForm, modelForm)
      this.modelForm.expireTime = [this.modelForm.startExpireTime, this.modelForm.endExpireTime]
      if (this.modelForm.password) {
        this.modelForm.shareType = 'password'
      }
    },
    async submitForm() {
      this.$refs['form'].validate(async valid => {
        if (valid) {
          switch (this.modelForm.shareType) {
            case 'publish':
              if (this.modelForm.password) {
                this.modelForm.password = ''
              }
              break
            case 'password':
              if (!this.modelForm.password) {
                this.modelForm.password = randomAlphaNumber(6)
              }
              break
          }
          if (this.modelForm.expireTime && this.modelForm.expireTime.length === 2) {
            this.modelForm.startExpireTime = this.modelForm.expireTime[0]
            this.modelForm.endExpireTime = this.modelForm.expireTime[1]
          } else {
            this.modelForm.startExpireTime = undefined
            this.modelForm.endExpireTime = undefined
          }
          this.buttonLoading = true
          await formApi.savePermission(this.modelForm)
          this.buttonLoading = false
          this.close()
        }
      })
    },
    copyUrl() {
      navigator.clipboard.writeText(this.url).then(() => this.$message.success('复制成功'))
    },
    openUrl() {
      window.open(this.url, '_target')
    },
    close() {
      this.$emit('update:visible', false)
    }
  }
}
</script>

<style scoped>
</style>
