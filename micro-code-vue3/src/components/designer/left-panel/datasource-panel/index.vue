<template>
  <div class="datasource-panel-container">
    <div class="header">
      <div>所有数据源</div>
      <div class="setting">
        <i
          class="el-icon-circle-plus-outline"
          title="新建"
          @click="handleDataSource"
        ></i>
      </div>
    </div>
    <el-tree
      :data="apis"
      :render-content="renderContent"
      node-key="id"
      @node-click="onNodeClick"
    />
    <RestApi
      :datasource="datasource"
      v-model:visible="restApiOpen"
      @change="getList"
    />
  </div>
</template>

<script lang="tsx">
import RestApi from "./component/RestApi/index.vue";
import { getRestApi, listRestApi } from "./component/RestApi/restApi";

export default {
  name: "DatasourcePanel",
  components: { RestApi },
  props: {
    designer: Object
  },
  data() {
    return {
      selectId: null,
      apis: [],
      restApiOpen: false,
      datasource: null
    };
  },
  mounted() {
    // this.getList();
  },
  methods: {
    getList() {
      listRestApi().then(res => {
        this.apis = res;
        this.designer.formConfig.apis = res;
      });
    },
    renderContent(h, { node, data, store }) {
      return
      <div class="datasource">
        <div>
          <span class="name">{data.name}</span>
        </div>
        <div>
          <el-dropdown
            onCommand={command => this.handleCommand(command, data)}
            size="mini"
            placement="bottom"
          >
            <i
              class={{ hide: this.isHide(node, data), "el-icon-more": true }}
            ></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="edit" icon="el-icon-edit">
                编辑
              </el-dropdown-item>
              <el-dropdown-item command="delete" icon="el-icon-delete">
                删除
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>;
    },
    onNodeClick(data) {
      this.selectId = data.id;
    },
    handleDataSource() {
      this.restApiOpen = true;
    },
    isHide(node, data) {
      return this.selectId != data.id;
    },
    handleEdit(data) {
      getRestApi(data.id).then(res => {
        this.datasource = res;
        this.restApiOpen = true;
      });
    },
    handleDelete(data) {
      this.$confirm("确定删除该数据源吗？", "提示", { type: "warning" }).then(
        () => {}
      );
    },
    handleCommand(command, data) {
      switch (command) {
        case "edit":
          this.handleEdit(data);
          break;
        case "delete":
          this.handleDelete(data);
          break;
      }
    }
  }
};
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
