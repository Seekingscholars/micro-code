<template>
  <div class="app-root">
    <div class="app-header">
      <div class="app-header-title">
        <el-tooltip
          content="上一级"
          effect="dark"
          placement="bottom"
        >
          <span
            class="app-header-title-name"
            @click="gotoDashboard"
          >
            <span>
              <i class="el-icon-d-arrow-left" />
            </span>
            <span>{{ app.appName }}<span />
            </span>
          </span>
        </el-tooltip>
        <el-tooltip
          content="新建表单"
          effect="dark"
          placement="bottom"
        >
          <span
            class="app-header-title-icon"
            @click="createAppForm"
          >
            <i class="el-icon-plus" />
          </span>
        </el-tooltip>
      </div>
    </div>
    <el-container class="app-container">
      <div class="app-aside">
        <el-scrollbar :style="{height:height+'px'}">
          <el-menu
            :default-active="selectFormId"
          >
            <el-menu-item
              v-for="item in formList"
              :key="item.id"
              :index="item.id.toString()"
              @click="()=>onFormClick(item)"
            >
              <div class="menu-item">
                <div>
                  <i class="el-icon-document" />
                  <span slot="title">{{ item.formName }}</span>
                </div>
                <div class="menu-item-toolbar">
                  <span @click="()=>handleFormEdit(item)"><i
                    class="el-icon-edit"
                    title="编辑"
                  /></span>
                  <span
                    style="color: #ef5c5c"
                    @click="()=>handleDeleteForm(item)"
                  ><i
                    class="el-icon-delete"
                    title="删除"
                  /></span>
                </div>
              </div>
            </el-menu-item>
          </el-menu>
        </el-scrollbar>
      </div>
      <div class="app-main">
        <FormRender
          v-if="flushed"
          :form-json="formJson"
          :data-model="dataModel"
        />
      </div>
    </el-container>
  </div>
</template>

<script>
import FormRender from '@/components/render/index.vue'
import appApi from '@/api/app'
import formApi from '@/api/form'
import { getRest } from './apiUtil'
export default {
  name: 'App',
  components: { FormRender },
  data() {
    return {
      appId: null,
      flushed: false,
      selectFormId: null,
      formList: [],
      dataModel: {},
      formJson: {},
      app: {}
    }
  },
  computed: {
    height: () => {
      return window.innerHeight - 90
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
      if (this.selectFormId !== item.id.toString()) {
        this.dataModel = {}
        await getRest(item.id, this.dataModel, this)
        this.selectFormId = item.id.toString()
        this.flushed = false
        const formData = await formApi.get({ id: item.id })
        this.formJson = JSON.parse(formData.formJson)
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
.menu-item{
  display: flex;
  justify-content: space-between;
  cursor: pointer;
}
.menu-item-toolbar{
display: none;
}
.menu-item:hover .menu-item-toolbar{
  display: inline-block;
}

</style>
