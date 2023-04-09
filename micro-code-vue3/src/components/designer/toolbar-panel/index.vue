<template>
  <div class="toolbar-container">
    <div class="left-toolbar">
      <el-button
        icon="el-icon-office-building"
        style="margin-left: 20px"
        title="组件层次结构树"
        @click="showNodeTreeDrawer"
      />
    </div>

<!--    <el-drawer-->
<!--      :destroy-on-close="true"-->
<!--      :modal="false"-->
<!--      :size="280"-->
<!--      v-model:visible="showNodeTreeDrawerFlag"-->
<!--      class="node-tree-drawer"-->
<!--      direction="ltr"-->
<!--      title="组件层次结构树"-->
<!--    >-->
<!--      <el-tree-->
<!--        ref="nodeTree"-->
<!--        :data="nodeTreeData"-->
<!--        :expand-on-click-node="false"-->
<!--        class="node-tree"-->
<!--        default-expand-all-->
<!--        icon-class="el-icon-arrow-right"-->
<!--        node-key="id"-->
<!--        @node-click="onNodeTreeClick"-->
<!--      >-->
<!--        <div slot-scope="{ node, data }" class="nodeTree">-->
<!--          <div>{{ node.label }}</div>-->
<!--          <div class="trash">-->
<!--            <el-button-->
<!--              icon="el-icon-delete"-->
<!--              type="text"-->
<!--              @click="() => removeWidgetById(data.id)"-->
<!--            />-->
<!--          </div>-->
<!--        </div>-->
<!--      </el-tree>-->
<!--    </el-drawer>-->

    <div class="right-toolbar">
      <div class="right-toolbar-con">
        <slot name="prefixToolbar"></slot>
        <el-button size="small" @click="clearFormWidget">清空</el-button>
        <el-button size="small" @click="previewForm">预览</el-button>
        <slot name="suffixToolbar"></slot>
      </div>
    </div>

  </div>
</template>

<script setup type="ts">
import {ref,nextTick} from 'vue';
import { useRouter } from "vue-router";
import { useAppStoreHook } from "@/store/modules/app";
const props=defineProps({designer:Object})
const  nodeTreeData=ref([]);
const router = useRouter();
function getLabel(widget) {
  if (!widget) {
    return;
  }
  let label;
  if (widget["form-item"]) {
    label = widget["form-item"].label;
    if (label) {
      return label;
    }
  }
  if (widget.options) {
    label = widget.options.label;
    if (label) {
      return label;
    }
  }
  label = widget.name;
  return label;
}
function buildTreeNodeOfWidget(widget, treeNode) {
  let curNode = {
    id: widget.id,
    label: getLabel(widget)
  };
  treeNode.push(curNode);
  if (widget.category === undefined) {
    return;
  }
  if (widget.tabs) {
    curNode.children = [];
    widget.tabs.map(tab => {
      let tabNode = {
        id: tab.id,
        label: getLabel(widget),
        children: []
      };
      curNode.children.push(tabNode);
      tab.widgetList.map(wChild => {
        buildTreeNodeOfWidget(wChild, tabNode.children);
      });
    });
  }
  if (widget.widgetList) {
    curNode.children = [];
    widget.widgetList.map(wChild => {
      buildTreeNodeOfWidget(wChild, curNode.children);
    });
  }
}

function refreshNodeTree() {
  nodeTreeData.value.length = 0;
  props.designer.widgetList.forEach(wItem => {
    buildTreeNodeOfWidget(wItem, nodeTreeData.value);
  });
}

function showNodeTreeDrawer() {
  refreshNodeTree();
  nextTick(() => {
    if (!!props.designer.selectedId) {
      //同步当前选中组件到节点树！！！
      // this.$refs.nodeTree.setCurrentKey(props.designer.selectedId);
    }
  });
}

function clearFormWidget() {
  props.designer.clearDesigner();
}

function previewForm() {
  const formJson={
      widgetList: props.designer.widgetList,
      formConfig: props.designer.formConfig
    }
  const to = router.resolve({
    name: "app-preview"
  });
  useAppStoreHook().setFormJson(formJson)
  window.open(to.href, "_blank");
}
function findWidgetById(widgetId) {
  return props.designer.findWidgetById(widgetId);
}

function onNodeTreeClick(nodeData) {
  const selectedId = nodeData.id;
  const foundW = findWidgetById(selectedId);
  if (!!foundW) {
    props.designer.setSelected(foundW);
  }
}
function removeWidgetById(widgetId) {
  props.designer.removeWidgetById(widgetId);
}
</script>

<style lang="scss" scoped>
div.toolbar-container {
  //min-width: 728px;  /* 解决工具按钮栏换行的问题 */
  /* 上一行css有问题，当窗口宽度不足时会把按钮挤出到右边的属性设置区，弃之！ */
  .nodeTree {
    flex: 1;
    display: flex;
    justify-content: space-between;
    font-size: 16px;
    line-height: 48px;

    .trash {
      display: none;
    }

    &:hover .trash {
      display: inline-block;
    }
  }
}

.left-toolbar {
  float: left;
  font-size: 16px;
}

.right-toolbar {
  float: right;
  text-align: right;
  overflow: hidden;

  .right-toolbar-con {
    text-align: left;
  }

  ::v-deep .el-button--text {
    font-size: 14px !important;
  }
}

.el-button i {
  margin-right: 3px;
}

.small-padding-dialog {
  ::v-deep .el-dialog__header {
    //padding-top: 3px;
    //padding-bottom: 3px;
    background: #f1f2f3;
  }

  ::v-deep .el-dialog__body {
    padding: 12px 15px 12px 15px;

    .el-alert.alert-padding {
      padding: 0 10px;
    }
  }

  ::v-deep .ace-container {
    border: 1px solid #dcdfe6;
  }
}

.dialog-title-light-bg {
  ::v-deep .el-dialog__header {
    background: #f1f2f3;
  }
}

.no-box-shadow {
  box-shadow: none;
}

.no-padding.el-tabs--border-card {
  ::v-deep .el-tabs__content {
    padding: 0;
  }
}

.node-tree-drawer ::v-deep {
  .el-drawer {
    padding: 10px;
    overflow: auto;
  }

  .el-drawer__header {
    margin-bottom: 12px;
    padding: 5px 5px 0;
  }

  .el-drawer__body {
    padding-left: 5px;
  }
}

/*.node-tree-scroll-bar {*/
/*  height: 100%;*/
/*  overflow: auto;*/
/*}*/

.node-tree ::v-deep {
  .el-tree > .el-tree-node:after {
    border-top: none;
  }

  .el-tree-node {
    position: relative;
    padding-left: 12px;
  }

  .el-tree-node__content {
    padding-left: 0 !important;
  }

  .el-tree-node__expand-icon.is-leaf {
    display: none;
  }

  .el-tree-node__children {
    padding-left: 12px;
    overflow: visible !important; /* 加入此行让el-tree宽度自动撑开，超出宽度el-draw自动出现水平滚动条！ */
  }

  .el-tree-node :last-child:before {
    height: 38px;
  }

  .el-tree > .el-tree-node:before {
    border-left: none;
  }

  .el-tree > .el-tree-node:after {
    border-top: none;
  }

  .el-tree-node:before {
    content: "";
    left: -4px;
    position: absolute;
    right: auto;
    border-width: 1px;
  }

  .el-tree-node:after {
    content: "";
    left: -4px;
    position: absolute;
    right: auto;
    border-width: 1px;
  }

  .el-tree-node:before {
    border-left: 1px dashed #4386c6;
    bottom: 0px;
    height: 100%;
    top: -10px;
    width: 1px;
  }

  .el-tree-node:after {
    border-top: 1px dashed #4386c6;
    height: 20px;
    top: 12px;
    width: 16px;
  }

  .el-tree-node.is-current > .el-tree-node__content {
    background: #c2d6ea !important;
  }

  .el-tree-node__expand-icon {
    margin-left: -3px;
    padding: 6px 6px 6px 0px;
    font-size: 16px;
  }
}
</style>
