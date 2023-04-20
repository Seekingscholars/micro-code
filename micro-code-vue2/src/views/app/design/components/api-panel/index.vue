<template>
  <div class="datasource-panel-container">
    <div class="header">
      <div>所有API</div>
      <el-tooltip content="新增api" effect="dark" placement="bottom">
        <div class="setting">
          <i class="el-icon-circle-plus-outline" @click="handleDataSource" />
        </div>
      </el-tooltip>
    </div>
    <el-tree
      :data="apis"
      :render-content="renderContent"
      node-key="id"
      @node-click="onNodeClick"
    />
    <RestApiDialog v-if="restApiOpen" :api="api" :visible.sync="restApiOpen" @change="getList" />
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
      restApiOpen: false,
      api: null
    }
  },
  methods: {
    async getList(formId) {
      this.apis = await restApi.list({ formId }) || []
      this.designer.formConfig.apis=this.apis
    },
    renderContent(h, { node, data, store }) {
      return (
        <div class='datasource'>
          <div>
            <span class='name'>{data.name}</span>
          </div>
          <div>
            <el-dropdown onCommand={command => this.handleCommand(command, data)} size='mini' placement='bottom'>
              <i class={{ 'hide': this.isHide(node, data), 'el-icon-more': true }}></i>
              <el-dropdown-menu slot='dropdown'>
                <el-dropdown-item command='edit' icon='el-icon-edit'>编辑</el-dropdown-item>
                <el-dropdown-item command='delete' icon='el-icon-delete'>删除</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </div>)
    },
    onNodeClick(data) {
      this.selectId = data.id
    },
    handleDataSource() {
      this.restApiOpen = true
    },
    isHide(node, data) {
      return this.selectId !== data.id
    },
    async handleEdit(data) {
      this.api = data
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
          this.handleEdit(data)
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

  .hide {
    display: none;
  }
}

</style>
