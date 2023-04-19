<template>
  <FormDesigner ref="FormDesignerRef">
    <template slot="header">
      <div class="form-design-header">
        <div class="header-title">
          <el-tooltip content="上一级" effect="dark" placement="bottom">
            <div class="header-title-back" @click="gotoApp">
              <i class="el-icon-d-arrow-left" />
            </div>
          </el-tooltip>
          <div class="header-title-name">
            <el-input v-model="modelForm.formName" size="small" @blur="!modelForm.formName?modelForm.formName='未命名表单':''" />
          </div>
        </div>
        <div class="header-right">
          <el-button
            icon="el-icon-search"
            @click="previewForm"
          >预览
          </el-button>
          <el-button
            icon="el-icon-plus"
            type="primary"
            @click="submitForm"
          >保存
          </el-button>
        </div>
      </div>
    </template>
  </FormDesigner>
</template>

<script>
import FormDesigner from '@/components/designer/index.vue'
import formApi from '@/api/form.api'
export default {
  components: { FormDesigner },
  data() {
    return {
      modelForm: {
        appId: '',
        formId: '',
        formName: '未命名表单',
        formJson: ''
      }
    }
  },
  created() {
    const formId = this.$route.params.formId
    this.modelForm.appId = this.$route.query.appId
    this.getForm(formId)
  },
  methods: {
    async getForm(formId) {
      if (formId && formId !== '0') {
        await formApi.get({ id: formId })
      }
    },
    gotoApp() {
      let to
      if (this.modelForm.appId) {
        to = this.$router.resolve({ path: '/app/overview/' + this.modelForm.appId })
      } else {
        to = this.$router.resolve({ path: '/' })
      }
      window.open(to.href, '_self')
    },
    previewForm() {
      const formJson = this.$refs.FormDesignerRef.getFormJson()
      const key = 'preview'
      const storage = window.sessionStorage
      storage.setItem(key, JSON.stringify(formJson))
      const to = this.$router.resolve({
        name: 'app-preview'
      })
      window.open(to.href, '_blank')
    },
    async submitForm() {
      this.modelForm.formJson = JSON.stringify(this.$refs.FormDesignerRef.getFormJson())
      await formApi.saveOrEdit(this.modelForm, !!this.modelForm.formId)
      this.gotoApp()
    }
  }
}
</script>
<style>
.form-design-header {
  display: flex;
  justify-content: space-between;
}
.form-design-header .header-title{
  padding: 5px 0 5px 10px;
  font-size: 18px;
  display: flex;
  line-height: 40px;
}
.form-design-header .header-title-back{
  cursor: pointer;
}
.form-design-header .header-title input{
  border: 0px;
  font-weight: bold;
  font-size: 18px;
  border-bottom: 1px dashed #b0b0b0;
}
.form-design-header .header-right {
  display: flex;
  height: 100%;
  line-height: 48px;
  margin-right: 20px;
}
</style>
