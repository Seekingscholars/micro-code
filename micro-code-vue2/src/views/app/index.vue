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
          @click="onFormClick"
        >
          <div>
            <i class="el-icon-menu" />
            <span class="app-form-title">{{ item.formName }}</span>
          </div>
          <div class="app-form-setting" @click.stop="selectFormId=item.id">
            <el-popover
              :value="selectFormId===item.id"
              placement="bottom"
              trigger="click"
            >
              <div class="app-form-setting-menu">
                <div class="app-form-setting-menu-item" @click="handleFormEdit"><i class="el-icon-edit" /><span
                  class="app-form-setting-menu-item-title"
                >编辑</span></div>
                <div class="app-form-setting-menu-item" style="color: #ef5c5c"><i class="el-icon-delete" /><span
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
import appApi from '@/api/app.api'
import formApi from '@/api/form.api'

export default {
  name: 'App',
  components: { FormRender },
  data() {
    return {
      formJson: JSON.parse('{"widgetList":[{"type":"chart","name":"非坐标图","icon":"","options":{"name":"chart18894648","span":24,"height":350,"type":"pie","data":"[\\n        { value: 1048, name: \'Search Engine\' },\\n        { value: 735, name: \'Direct\' },\\n        { value: 580, name: \'Email\' },\\n        { value: 484, name: \'Union Ads\' },\\n        { value: 300, name: \'Video Ads\' }\\n      ]"},"chart-title":{"hidden":false,"title":"你好呀","color":"#333333","fontSize":18,"left":"left","top":"top"},"chart-legend":{"hidden":false,"icon":"rect","orient":"horizontal","left":"center","top":"top"},"id":"chart18894648"},{"type":"chart","name":"坐标图","icon":"","options":{"name":"chart18913546","span":24,"height":350,"dimension":"product","flip":false,"data":"[{\\"product\\":\\"Mon\\",\\"data1\\":120,\\"data2\\":130},{\\"product\\":\\"Tue\\",\\"data1\\":200,\\"data2\\":130},{\\"product\\":\\"Wed\\",\\"data1\\":150,\\"data2\\":312},{\\"product\\":\\"Thu\\",\\"data1\\":80,\\"data2\\":268},{\\"product\\":\\"Fri\\",\\"data1\\":70,\\"data2\\":155},{\\"product\\":\\"Sat\\",\\"data1\\":110,\\"data2\\":117},{\\"product\\":\\"Sun\\",\\"data1\\":130,\\"data2\\":160}]","items":[{"id":"item1","value":"data1","label":"","type":"bar","color":""},{"id":"item11645573","value":"data2","label":"","disabled":false,"type":"bar"}]},"chart-title":{"hidden":false,"title":"","color":"#333333","fontSize":18,"left":"left","top":"top"},"chart-legend":{"hidden":false,"icon":"rect","orient":"horizontal","left":"center","top":"top"},"chart-label":{"hidden":false,"position":"top"},"chart-xAxis":{"hidden":false,"rotate":0,"color":"#333333"},"chart-yAxis":{"hidden":false,"rotate":0,"color":"#333333"},"id":"chart18913546"}],"formConfig":{"size":"","labelWidth":80,"labelPosition":"left","disabled":false,"dataCode":"","functionCode":"","onCreated":"","onMounted":"","apis":[{"id":"1636693583371771905","name":"listUser","url":"/monitor/operlog/list","method":"GET","timeout":5000,"remark":null,"header":null,"param":"...searchForm","body":null,"successFunction":"users=res.rows","failFunction":null}],"labelAlign":"label-left-align","modelName":"formData","refName":"vForm","rulesName":"rules","cssCode":"","customClass":"","data":"","functions":"","layoutType":"PC","onFormDataChange":"","onFormCreated":"","onFormMounted":"listUser()","datasourceApis":[{"id":"1636693583371771905","name":"listUser","url":"/monitor/operlog/list","method":"GET","timeout":5000,"remark":null,"header":null,"param":"...searchForm","body":null,"successFunction":"users=res.rows","failFunction":null}]}}'),
      flushed: true,
      selectFormId: null,
      formList: [],
      app: {}
    }
  },
  created() {
    const appId = this.$route.params.appId
    this.getApp(appId)
  },
  mounted() {
    document.addEventListener('click', this.handleDocumentClick, false)
  },
  destroyed() {
    document.removeEventListener('click', this.handleDocumentClick, false)
  },
  methods: {
    handleDocumentClick() {
      this.selectFormId = null
    },
    createAppForm() {
      const to = this.$router.resolve({ path: '/app/design/0', query: { appId: this.app.id }})
      window.open(to.href, '_self')
    },
    handleFormEdit() {
      this.handleDocumentClick()
      const to = this.$router.resolve({ name: 'app-design' })
      window.open(to.href, '_target')
    },
    onFormClick(item) {
      this.flushed = false
      this.$nextTick(() => {
        setTimeout(() => this.flushed = true, 100)
      })
    },
    gotoDashboard() {
      const to = this.$router.resolve({ path: '/' })
      window.open(to.href, '_self')
    },
    async getApp(appId) {
      this.app = await appApi.get({ id: appId }) || {}
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
