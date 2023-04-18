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
import {nextTick, ref} from 'vue';
import {useRouter} from "vue-router";

const props = defineProps({designer: Object})
const nodeTreeData = ref([]);
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
  const formJson = {
    widgetList: props.designer.widgetList,
    formConfig: props.designer.formConfig
  }
  const key = 'preview'
  const storage = window.localStorage
  storage.setItem(key, JSON.stringify(formJson))
  const to = router.resolve({
    name: "app-preview"
  });
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
