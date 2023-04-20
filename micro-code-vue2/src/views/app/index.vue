<template>
  <div class="app-root">
    <div class="app-header">
      <div class="app-header-title">
        <el-tooltip content="上一级" effect="dark" placement="bottom">
          <span class="app-header-title-name" @click="gotoDashboard">
            <span>
              <i class="el-icon-d-arrow-left" />
            </span>
            <span>{{ app.appName }}<span />
            </span>
          </span>
        </el-tooltip>
        <el-tooltip content="新建表单" effect="dark" placement="bottom">
          <span class="app-header-title-icon" @click="createAppForm">
            <i class="el-icon-plus" />
          </span>
        </el-tooltip>
      </div>
    </div>
    <div class="app-container">
      <div class="app-aside">
        <div
          v-for="item in formList"
          :key="item.id"
          :class="{'app-form':true,'app-form-selected':selectFormId===item.id}"
          @click="()=>onFormClick(item)"
          @dblclick="()=>handleFormEdit(item)"
        >
          <div>
            <i class="el-icon-menu" />
            <span class="app-form-title">{{ item.formName }}</span>
          </div>
          <div class="app-form-setting" @click.stop>
            <el-popover
              placement="bottom"
              trigger="click"
            >
              <div class="app-form-setting-menu">
                <div class="app-form-setting-menu-item" @click="()=>handleFormEdit(item)"><i class="el-icon-edit" /><span
                  class="app-form-setting-menu-item-title"
                >编辑</span></div>
                <div class="app-form-setting-menu-item" style="color: #ef5c5c" @click="()=>handleDeleteForm(item)"><i class="el-icon-delete" /><span
                  class="app-form-setting-menu-item-title"
                >删除</span></div>
              </div>
              <div slot="reference"><i class="el-icon-more" /></div>
            </el-popover>
          </div>
        </div>
      </div>
      <div class="app-main">
        <FormRender v-if="flushed" :form-json="formJson" />
      </div>
    </div>
  </div>
</template>

<script>
import FormRender from '@/components/render/index.vue'
import appApi from '@/api/app'
import formApi from '@/api/form'

export default {
  name: 'App',
  components: { FormRender },
  data() {
    return {
      appId: null,
      flushed: true,
      selectFormId: null,
      formList: [],
      formData: {},
      app: {}
    }
  },
  computed: {
    formJson() {
      if (this.formData.formJson) {
        return JSON.parse(this.formData.formJson)
      } else {
        return {
          widgetList: [],
          formConfig: {}
        }
      }
    }
  },
  created() {
    this.appId = this.$route.params.appId
    this.getApp()
    this.listForm()
  },
  methods: {
    createAppForm() {
      const to = this.$router.resolve({ path: '/app/design/0', query: { appId: this.app.id }})
      window.open(to.href, '_self')
    },
    handleFormEdit(form) {
      const to = this.$router.resolve({ path: '/app/design/' + form.id })
      window.open(to.href, '_self')
    },
    handleDeleteForm(form) {
      this.$confirm('您确定要删除表单“' + form.formName + '”吗？', '警告', { type: 'warning' }).then(async() => {
        await formApi.remove({ id: form.id })
        await this.listForm()
      })
    },
    async onFormClick(item) {
      if (this.selectFormId !== item.id) {
        this.selectFormId = item.id
        this.flushed = false
        this.formData = await formApi.get({ id: item.id }) || {}
        this.$nextTick(() => {
          this.flushed = true
        })
      }
    },
    gotoDashboard() {
      const to = this.$router.resolve({ path: '/' })
      window.open(to.href, '_self')
    },
    async getApp() {
      this.app = await appApi.get({ id: this.appId }) || {}
    },
    async listForm() {
      this.formList = await formApi.list({ appId: this.appId }) || []
      if (this.formList.length > 0) {
        await this.onFormClick(this.formList[0])
      }
    }
  }
}
</script>

<style scoped>
.app-root {
  height: 100%;
  width: 100%;
}

.app-header {
  height: 48px;
  width: 100%;
  border-bottom: 1px solid #e8e8e8;
  font-weight: bold;
  font-size: 18px;
  line-height: 48px;
}

.app-header-title {
  margin-left: 10px;
}

.app-header-title-name {
  cursor: pointer;
  margin-left: 5px;
}

.app-header-title-icon {
  margin-left: 10px;
  cursor: pointer;
}

.app-container {
  display: flex;
  height: calc(100% - 48px);
  width: 100%;
}

.app-aside {
  border-right: 1px solid #e8e8e8;
  width: 300px;
  height: 100%;
}

.app-main {
  flex: 1;
  padding: 10px;
}

.app-form {
  padding: 15px 5px 15px 15px;
  display: flex;
  justify-content: space-between;
  /*cursor: pointer;*/
  /*border-radius: 12px;*/
}

.app-form-title {
  margin-left: 5px;
}

.app-form-setting {
  display: none;
}

.app-form:hover {
  background-color: #f6f4f4;
}

.app-form:hover .app-form-setting {
  display: inline-block;
}

.app-form-selected{
  background-color: #eaecfd;
}

.app-form-selected .app-form-setting {
  display: inline-block;
}

.app-form-setting-menu-item {
  padding: 5px;
  cursor: pointer;
}

.app-form-setting-menu-item:hover {
  background-color: #eaecfd;
}

.app-form-setting-menu-item-title {
  margin-left: 5px;
}
</style>
