<template>
  <div class="datasource-panel-container">
    <div class="header">
      <div>所有API</div>
      <el-tooltip
        content="新增api"
        effect="dark"
        placement="bottom"
      >
        <div class="setting">
          <i
            class="el-icon-circle-plus-outline"
            @click="handleCreateApi"
          />
        </div>
      </el-tooltip>
    </div>
    <el-tree
      :data="apis"
      :render-content="renderContent"
      node-key="id"
      @node-click="onNodeClick"
    />
    <RestApiDialog
      v-if="restApiOpen"
      :api="api"
      :visible.sync="restApiOpen"
      @change="()=>getList(formId)"
    />
  </div>
</template>

<script>
import RestApiDialog from './component/RestApiDialog'
import restApi from '@/api/rest.api'
export default {
  name: 'DatasourcePanel',
  components: { RestApiDialog },
  props: {
    designer: Object
  },
  data() {
    return {
      selectId: null,
      apis: [],
      formId: null,
      restApiOpen: false,
      api: null
    }
  },
  created() {
    const formId = this.$route.params.formId
    this.getList(formId)
  },
  methods: {
    async getList(formId) {
      this.formId = formId
      const apis = await restApi.list({ formId }) || []
      this.designer.formConfig.apis = apis
      this.apis = apis
    },
    renderContent(h, { node, data, store }) {
      return (
        <div class='menu-item'>
          <div>
            <span slot='title'>{data.name}</span>
          </div>
          <div class='menu-item-toolbar'>
            <span onClick={() => this.handleEditApi(data)}><i class='el-icon-edit' title='编辑'/></span>
            <span
              style='color: #ef5c5c;margin-left:10px;margin-right:10px;'
              onClick={() => this.handleDelete(data)}
            ><i class='el-icon-delete' title='删除'/></span>
          </div>
        </div>)
    },
    onNodeClick(data) {
      this.selectId = data.id
    },
    handleCreateApi() {
      this.api = { formId: this.formId }
      this.restApiOpen = true
    },
    isHide(node, data) {
      return this.selectId !== data.id
    },
    async handleEditApi(data) {
      this.api = { ...data, formId: this.formId }
      this.restApiOpen = true
    },
    handleDelete(data) {
      this.$confirm('确定删除数据源吗？', '提示', { type: 'warning' }).then(
        async() => {
          await restApi.remove({ id: data.id })
        }
      )
    },
    handleCommand(command, data) {
      switch (command) {
        case 'edit':
          this.handleEditApi(data)
          break
        case 'delete':
          this.handleDelete(data)
          break
      }
    }
  }
}
</script>

<style lang="scss">
.datasource-panel-container {
  padding-bottom: 10px;

  .header {
    display: flex;
    justify-content: space-between;
    padding: 0px 20px 10px 20px;
  }

  .datasource {
    flex: 1;
    display: flex;
    justify-content: space-between;
    font-size: 14px;
    padding-right: 20px;
  }

  .setting {
    font-size: 20px;
    cursor: pointer;
  }
}
.menu-item{
  display: flex;
  justify-content: space-between;
  cursor: pointer;
  width: 100%;
}
.menu-item-toolbar{
  display: none;
}
.menu-item:hover .menu-item-toolbar{
  display: inline-block;
}

</style>
