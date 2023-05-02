<template>
  <div>
    <FormDesigner ref="FormDesignerRef">
      <template #header>
        <div class="form-design-header">
          <div class="header-title">
            <el-tooltip
              content="上一级"
              effect="dark"
              placement="bottom"
            >
              <div
                class="header-title-back"
                @click="gotoApp"
              >
                <i class="el-icon-d-arrow-left" />
              </div>
            </el-tooltip>
            <div class="header-title-name">
              <el-input
                v-model="modelForm.formName"
                size="small"
                @blur="!modelForm.formName?modelForm.formName='未命名表单':''"
              />
            </div>
          </div>
          <div class="header-right">
            <el-button
              size="small"
              icon="el-icon-search"
              type="warning"
              round
              @click="previewForm"
            >预览
            </el-button>
            <el-button
              size="small"
              icon="el-icon-plus"
              type="primary"
              round
              @click="submitForm"
            >保存
            </el-button>
            <el-button
              size="small"
              icon="el-icon-link"
              type="success"
              round
              @click="shareForm"
            >分享
            </el-button>

          </div>
        </div>
      </template>
      <template #left="{designer}">
        <el-tab-pane
          label="数据源"
          name="datasource"
        >
          <div slot="label">
            <el-tooltip
              content="数据源"
              placement="right"
            >
              <div class="icon-label"><i class="el-icon-coin" /></div>
            </el-tooltip>
          </div>
          <el-scrollbar class="scrollbar">
            <ApiPanel
              ref="ApiPanelRef"
              :designer="designer"
            />
          </el-scrollbar>
        </el-tab-pane>
      </template>
    </FormDesigner>
    <FormPermission
      v-if="showFormPermissionFlag"
      :visible.sync="showFormPermissionFlag"
      :form="modelForm"
    />
  </div>
</template>

<script>
import FormDesigner from '@/components/designer/index.vue'
import formApi from '@/api/form'
import ApiPanel from './components/api-panel/index'
import FormPermission from './components/form-permission/index'
export default {
  components: { FormDesigner, ApiPanel, FormPermission },
  data() {
    return {
      showFormPermissionFlag: false,
      modelForm: {
        id: '',
        appId: '',
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
      let widgetJson = await import('@/views/app/widget/widget.json')
      let collapseJson = await import('@/views/app/widget/collapse.json')
      widgetJson = widgetJson.default
      collapseJson = collapseJson.default
      if (formId && formId !== '0') {
        this.modelForm = Object.assign(this.modelForm, await formApi.get({ id: formId }))
        this.$nextTick(() => {
          const formJson = JSON.parse(this.modelForm.formJson)
          formJson.widgetJson = widgetJson
          formJson.collapseJson = collapseJson
          this.$refs.FormDesignerRef.setFormJson(formJson)
        })
      } else {
        this.$refs.FormDesignerRef.setFormJson({
          widgetJson: widgetJson,
          collapseJson: collapseJson
        })
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
        path: '/app/preview/0'
      })
      window.open(to.href, '_blank')
    },
    async submitForm() {
      this.modelForm.formJson = JSON.stringify(this.$refs.FormDesignerRef.getFormJson())
      await formApi.saveOrEdit(this.modelForm, !!this.modelForm.id)
    },
    shareForm() {
      this.showFormPermissionFlag = true
      // const to = this.$router.resolve({
      //   path: '/app/preview/fghfghfjhgj'
      // })
      // console.log(to)
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
.form-design-header .header-center{
  display: flex;
  line-height: 48px;
}
.form-design-header .header-center .header-center-toolbar{
  margin-right: 10px;
  cursor: pointer;
}
.form-design-header .header-right {
  display: flex;
  height: 100%;
  margin-right: 20px;
  padding: 5px;
}
</style>
